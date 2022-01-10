package pl.company.carservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import pl.company.carservice.dto.CarCustomerServiceTaskDto;
import pl.company.carservice.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Task save(Task entity);

    Optional<Task> findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<Task> findAllDtoBy();

    @Query("SELECT new pl.company.carservice.dto.CarCustomerServiceTaskDto(" +
            "car.brand, car.model, " +
            "customer.name, customer.surname," +
            "service.name," +
            "task.id, task.acceptanceDate, task.completionDate, task.serviceDescription, task.problemDescription) FROM Task AS task " +
            "INNER JOIN task.car AS car " +
            "INNER JOIN task.customer AS customer " +
            "INNER JOIN task.service as service")
    Page<CarCustomerServiceTaskDto> findAllDto(Pageable pageable);
}
