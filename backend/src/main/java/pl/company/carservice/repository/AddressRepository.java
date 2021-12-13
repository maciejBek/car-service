package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
