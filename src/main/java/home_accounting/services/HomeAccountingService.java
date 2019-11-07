package home_accounting.services;

import home_accounting.entity.AccountingEnum;
import home_accounting.entity.AccountingPeriod;
import home_accounting.entity.HomeAccounting;
import home_accounting.repository.HomeAccountingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import home_accounting.entity.CustomUser;

@Service
public class HomeAccountingService {

    @Autowired
    private HomeAccountingRepository homeAccountingRepository;

    @Transactional
    public boolean addUser(AccountingPeriod accounting_period,
                           CustomUser custom_user,
                           AccountingEnum type,
                           String description,
                           Long value  ) {


        HomeAccounting homeAccounting = new HomeAccounting(accounting_period,
                  custom_user,
                  type,
                  description,
                  value);
        homeAccountingRepository.save(homeAccounting);

        return true;
    }
}
