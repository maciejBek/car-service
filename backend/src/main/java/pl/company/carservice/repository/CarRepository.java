package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    Long removeById(Long id);

    boolean existsById(Long id);

//    Long removeByBrand(String brand);
//    List<Car> deleteByBrand(String bradn);

}
