package pl.company.carservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.company.carservice.dto.CarIdBrandModelDto;
import pl.company.carservice.model.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

    Long removeById(Long id);

    boolean existsById(Long id);

    Page<CarIdBrandModelDto> findAllById(Long id, Pageable pageable);

    Page<CarIdBrandModelDto> findAllBy(Pageable pageable);

}
