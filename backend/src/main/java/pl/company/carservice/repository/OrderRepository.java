package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Assignment;

public interface OrderRepository extends JpaRepository<Assignment, Long> {
}
