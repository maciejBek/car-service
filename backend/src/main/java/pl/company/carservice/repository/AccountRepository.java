package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByUsername(String username);

    boolean existsByPassword(String password);

    boolean existsByUsernameIsContainingAndPassword(String username, String password);
}
