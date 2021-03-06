package pl.company.carservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.company.carservice.dto.CarCustomerServiceTaskDto;
import pl.company.carservice.model.Task;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Task save(Task entity);

    Optional<Task> findById(Long id);

    List<Task> findAllByCustomerId(Long customerId);

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

    @Query("SELECT new pl.company.carservice.dto.CarCustomerServiceTaskDto(" +
            "car.brand, car.model, " +
            "customer.name, customer.surname," +
            "service.name," +
            "task.id, task.acceptanceDate, task.completionDate, task.serviceDescription, task.problemDescription) FROM Task AS task " +
            "INNER JOIN task.car AS car " +
            "INNER JOIN task.customer AS customer " +
            "INNER JOIN task.service AS service " +
            "WHERE customer.id  = ?1")
    List<CarCustomerServiceTaskDto> findAllDtoByCustomerId(Long customerId);

    // insert addedBillId WHERE task.id = taskId
    @Transactional
    @Modifying
    @Query("UPDATE Task task SET task.bill.id = ?2 WHERE task.id = ?1")
    void updateBillId(Long taskId, Long billId);

    @Transactional
    @Modifying
    @Query("UPDATE Task task SET task.completionDate = ?2 WHERE task.id = ?1")
    void markAsCompleted(Long id, LocalDateTime completionDate);
}
