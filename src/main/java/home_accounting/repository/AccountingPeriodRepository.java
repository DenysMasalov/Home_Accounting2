package home_accounting.repository;

import home_accounting.entity.AccountingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AccountingPeriodRepository extends JpaRepository<AccountingPeriod, Long> {

    @Query("SELECT u FROM AccountingPeriod u where u.custom_user  = :custom_user_id")
     List<AccountingPeriod> findByLogin(@Param("custom_user_id") String custom_user_id);
}
