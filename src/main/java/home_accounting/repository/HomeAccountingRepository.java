package home_accounting.repository;

import home_accounting.entity.HomeAccounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeAccountingRepository extends JpaRepository<HomeAccounting, Long> {

    @Query("SELECT u FROM HomeAccounting u where u.custom_user.login  = :login ")
    List<HomeAccounting> findByLogin(@Param("login") String login);

    @Query("SELECT u FROM HomeAccounting u where u.custom_user.login  = :loginand " +
                     " and  u.accounting_period.period = :period")
    List<HomeAccounting> findByLoginAndPeriod(@Param("login") String login,
                                              @Param("period") String period);

}
