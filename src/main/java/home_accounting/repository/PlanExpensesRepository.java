package home_accounting.repository;

import home_accounting.entity.PlanExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanExpensesRepository extends JpaRepository<PlanExpenses, Long> {
}
