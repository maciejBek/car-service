package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
