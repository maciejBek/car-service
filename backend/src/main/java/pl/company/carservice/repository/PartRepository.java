package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.Part;

public interface PartRepository extends JpaRepository<Part, Long>  {

}
