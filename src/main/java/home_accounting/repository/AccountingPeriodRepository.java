package home_accounting.repository;

import home_accounting.entity.AccountingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountingPeriodRepository extends JpaRepository<AccountingPeriod, Long> {
}
