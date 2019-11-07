package home_accounting.repository;

import home_accounting.entity.PlanExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanGainsRepository extends JpaRepository<PlanExpenses, Long> {
}
