package pl.company.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.company.carservice.model.NeededTaskParts;

public interface NeededTaskPartsRepository extends JpaRepository<NeededTaskParts, Long> {

    boolean existsByPartNumberingAndPartSerialNumber(String numbering, String serialNumber);
}
