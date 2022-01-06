package pl.company.carservice.repository;

import org.springframework.data.repository.Repository;
import pl.company.carservice.dto.CustomerIdNameSurnameDto;
import pl.company.carservice.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends Repository<Customer, Long> {

    Customer save(Customer entity);

    List<CustomerIdNameSurnameDto> findAllDtoBy();

    Optional<Customer> findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);

    Customer getById(Long id);
}
