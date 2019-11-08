package home_accounting;

import home_accounting.entity.AccountingPeriod;
import home_accounting.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import home_accounting.entity.CustomUser;

import java.util.Collection;

@Controller
public class MyController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountingPeriodService accountingPeriodService;

    @Autowired
    private PlainGainsService plainGainsService;

    @Autowired
    private AccountingService accountingService;

    @Autowired
    private HomeAccountingService homeAccountingService;

    @Autowired
    private PlainExpensesService plainExpensesService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String index(Model model){
        User user = (User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String login = user.getUsername();
        CustomUser dbUser = userService.findByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("admin", isAdmin(user));
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "index";
    }

    private boolean isAdmin(User user) {
        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority auth : roles) {
            if ("ROLE_ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String login = user.getUsername();
        userService.updateUser(login, email, phone);

        return "redirect:/";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone,
                         Model model) {
        String passHash = passwordEncoder.encodePassword(password, null);

        if ("".equals(login) ||
                !userService.addUser(login, passHash, UserRole.USER, email, phone)) {
            model.addAttribute("exists", true);
            model.addAttribute("login", login);
            return "register";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/addperiod", method = RequestMethod.POST)
    public String newPeriod(@RequestParam String period,
                         Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = user.getUsername();

        if ("".equals(period) ||
                !accountingPeriodService.add(login, period)) {
            return "newperiod";
        }

        return "redirect:/period";
    }

    @RequestMapping(value = "/addplangain", method = RequestMethod.POST)
    public String addplangain(@RequestParam String description,
                              @RequestParam String value,
                            Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = user.getUsername();

        if ("".equals(description) ||"".equals(value) ||
                !plainGainsService.add(login, description, Long.parseLong(value))) {
            return "newplangain";
        }

        return "redirect:/plangains";
    }

    @RequestMapping(value = "/addplanexpense", method = RequestMethod.POST)
    public String addplanexpense(@RequestParam String description,
                                 @RequestParam String value,
                            Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = user.getUsername();

        if ("".equals(description) ||"".equals(value) ||
                !plainExpensesService.add(login, description, Long.parseLong(value))) {
            return "newplanexpense";
        }

        return "redirect:/planexpenses";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(name = "toDelete[]", required = false)
                                 long[] ids,
                         Model model) {
        if (ids != null && ids.length > 0)
            userService.deleteUsers(ids);

        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/deleteperiod", method = RequestMethod.POST)
    public String deleteperiod(@RequestParam(name = "toDelete[]", required = false)
                                 long[] ids,
                         Model model) {
        if (ids != null && ids.length > 0)
            accountingPeriodService.deletePeriods(ids);

       // model.addAttribute("users", userService.getAllUsers());
        return "redirect:/period";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/newperiod")
    public String newperiod() {
        return "newperiod";
    }

    @RequestMapping("/newplangain")
    public String newplangain() {
        return "newplangain";
    }

    @RequestMapping("/newplanexpense")
    public String newplanexpense() {
        return "newplanexpense";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @RequestMapping("/period")
    public String accountingPeriod(Model model) {
        model.addAttribute("periods", accountingPeriodService.getAllPeriods());
        return "periods";
    }

    @RequestMapping("/plangains")
    public String planGains(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = user.getUsername();
        model.addAttribute("plangains", plainGainsService.getAllForUser(login));
        return "plangains";
    }

    @RequestMapping("/planexpenses")
    public String planExpenses(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = user.getUsername();
        model.addAttribute("planexpenses", plainExpensesService.getAllForUser(login));
        return "planexpenses";
    }

    @RequestMapping("/homeaccounting")
    public String homeAccountings(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = user.getUsername();
        model.addAttribute("homeaccountings", homeAccountingService.getAllForUser(login));
        return "homeaccounting";
    }

    @RequestMapping("/accountinghistory")
    public String accountings(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String login = user.getUsername();
        model.addAttribute("accountings", accountingService.getAllForUser(login));
        return "accounting";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }

}
