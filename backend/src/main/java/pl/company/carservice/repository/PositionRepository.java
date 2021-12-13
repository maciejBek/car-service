package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
