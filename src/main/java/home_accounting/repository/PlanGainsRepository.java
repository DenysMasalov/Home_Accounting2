package home_accounting.repository;


import home_accounting.entity.PlanGains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanGainsRepository extends JpaRepository<PlanGains, Long> {

    @Query("SELECT u FROM PlanGains u where u.custom_user.login  = :login")
    List<PlanGains> findByLogin(@Param("login") String login);

}
