package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
