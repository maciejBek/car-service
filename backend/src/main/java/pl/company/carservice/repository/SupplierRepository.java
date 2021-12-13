package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
