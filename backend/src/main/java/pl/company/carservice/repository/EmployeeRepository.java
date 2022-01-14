package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {

    Optional<Employee> findByAccount_Id(Long id);
}
