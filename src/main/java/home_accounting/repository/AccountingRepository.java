package home_accounting.repository;

import home_accounting.entity.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountingRepository extends JpaRepository<Accounting, Long> {

    @Query("SELECT u FROM Accounting u where u.custom_user.login  = :login")
    List<Accounting> findByLogin(@Param("login") String login);

}
