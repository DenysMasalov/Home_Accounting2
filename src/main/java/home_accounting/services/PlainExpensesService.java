package home_accounting.services;

import home_accounting.entity.CustomUser;
import home_accounting.entity.PlanExpenses;
import home_accounting.entity.PlanGains;
import home_accounting.repository.PlanExpensesRepository;
import home_accounting.repository.PlanGainsRepository;
import home_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlainExpensesService {
    @Autowired
    private PlanExpensesRepository planExpensesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<PlanExpenses> getAll() {
        return planExpensesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<PlanExpenses> getAllForUser(String login) {
        return planExpensesRepository.findByLogin(login);
    }

    @Transactional
    public boolean add(String login, String description, Long value) {

        if (!userRepository.existsByLogin(login))
            return false;

        CustomUser customUser =  userService.findByLogin(login);

        PlanExpenses planExpenses = new PlanExpenses(customUser, description, value);
        planExpensesRepository.save(planExpenses);

        return true;
    }

    @Transactional
    public void delete(long[] ids) {
        if (ids == null) return;

        for (long id : ids) {
            planExpensesRepository.delete(id);
        }
    }
}
