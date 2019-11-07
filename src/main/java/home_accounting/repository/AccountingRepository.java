package home_accounting.repository;

import home_accounting.entity.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingRepository extends JpaRepository<Accounting, Long> {
}
