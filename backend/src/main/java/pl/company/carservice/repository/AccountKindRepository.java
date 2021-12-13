package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.AccountKind;

public interface AccountKindRepository extends JpaRepository<AccountKind, Long> {
}
