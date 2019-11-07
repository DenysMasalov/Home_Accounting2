package home_accounting.services;

import home_accounting.entity.AccountingPeriod;
import home_accounting.entity.CustomUser;
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

    @Transactional(readOnly = true)
    public List<AccountingPeriod> getAllPeriods() {
        return accountingPeriodRepository.findAll();
    }

    @Transactional
    public boolean addPeriod(String login, String period) {

        if (!userRepository.existsByLogin(login))
            return false;

        CustomUser customUser =  userService.findByLogin(login);

        AccountingPeriod accountingPeriod = new AccountingPeriod(customUser, period);
        accountingPeriodRepository.save(accountingPeriod);

        return true;
    }


}
