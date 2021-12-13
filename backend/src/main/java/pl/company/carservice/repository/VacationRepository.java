package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
}
