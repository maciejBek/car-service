package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
