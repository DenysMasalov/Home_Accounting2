package home_accounting.services;

import home_accounting.entity.*;
import home_accounting.enums.AccountingEnum;
import home_accounting.repository.AccountingPeriodRepository;
import home_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountingPeriodService {

    @Autowired
    private AccountingPeriodRepository accountingPeriodRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlainGainsService plainGainsService;

    @Autowired
    private PlainExpensesService plainExpensesService;

    @Autowired
    private HomeAccountingService homeAccountingService;

    @Transactional(readOnly = true)
    public List<AccountingPeriod> getAllPeriods() {
        return accountingPeriodRepository.findAll();
    }

    @Transactional
    public boolean add(String login, String period) {

        if (!userRepository.existsByLogin(login))
            return false;

        CustomUser customUser =  userService.findByLogin(login);

        AccountingPeriod accountingPeriod = new AccountingPeriod(customUser, period);
        accountingPeriodRepository.save(accountingPeriod);


        List<PlanGains> planGainsList = plainGainsService.getAllForUser(login);
        for (int i = 0; i < planGainsList.size(); i++) {
            PlanGains planGains = planGainsList.get(i);
            homeAccountingService.add(accountingPeriod,
                    customUser,
                    AccountingEnum.GAIN,
                    planGains.getDescription(),
                    planGains.getValue());
        }

        List<PlanExpenses> planExpensesList = plainExpensesService.getAllForUser(login);
        for (int i = 0; i < planExpensesList.size(); i++) {
            PlanExpenses planExpenses = planExpensesList.get(i);
            homeAccountingService.add(accountingPeriod,
                    customUser,
                    AccountingEnum.EXPENSE,
                    planExpenses.getDescription(),
                    planExpenses.getValue());
        }
        return true;
    }

    @Transactional
    public void deletePeriods(long[] ids) {
        if (ids == null) return;

        for (long id : ids) {
            accountingPeriodRepository.delete(id);
        }
    }

}
