package home_accounting.repository;

import home_accounting.entity.PlanExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanExpensesRepository extends JpaRepository<PlanExpenses, Long> {

    @Query("SELECT u FROM PlanExpenses u where u.custom_user.login  = :login")
    List<PlanExpenses> findByLogin(@Param("login") String login);

}
