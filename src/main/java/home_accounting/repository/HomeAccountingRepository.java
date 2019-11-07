package home_accounting.repository;

import home_accounting.entity.HomeAccounting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeAccountingRepository extends JpaRepository<HomeAccounting, Long> {
}
