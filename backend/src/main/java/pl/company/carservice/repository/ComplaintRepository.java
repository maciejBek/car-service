package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
