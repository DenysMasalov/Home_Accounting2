package home_accounting.services;

import home_accounting.enums.AccountingEnum;
import home_accounting.entity.AccountingPeriod;
import home_accounting.entity.HomeAccounting;
import home_accounting.repository.HomeAccountingRepository;
import home_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import home_accounting.entity.CustomUser;

import java.util.List;

@Service
public class HomeAccountingService {

    @Autowired
    private HomeAccountingRepository homeAccountingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<HomeAccounting> getAll() {
        return homeAccountingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<HomeAccounting> getAllForUser(String login) {
        return homeAccountingRepository.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public List<HomeAccounting> getAllForUserAndPeriod(String login, String period) {
        return homeAccountingRepository.findByLoginAndPeriod(login, period);
    }

}
