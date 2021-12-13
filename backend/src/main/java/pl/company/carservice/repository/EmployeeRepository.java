package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {

}
