package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
