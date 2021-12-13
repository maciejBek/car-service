package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
