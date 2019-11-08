package home_accounting.services;

import home_accounting.entity.Accounting;
import home_accounting.entity.CustomUser;
import home_accounting.repository.AccountingRepository;
import home_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountingService {

    @Autowired
    private AccountingRepository accountingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Accounting> getAll() {
        return accountingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Accounting> getAllForUser(String login) {
        return accountingRepository.findByLogin(login);
    }

}
