package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
