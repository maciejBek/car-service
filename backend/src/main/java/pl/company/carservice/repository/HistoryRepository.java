package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
