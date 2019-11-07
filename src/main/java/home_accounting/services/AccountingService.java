package home_accounting.services;

import home_accounting.repository.AccountingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingService {

    @Autowired
    private AccountingRepository accountingRepositoryy;


}
