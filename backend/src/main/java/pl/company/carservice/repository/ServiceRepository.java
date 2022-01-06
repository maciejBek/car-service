package pl.company.carservice.repository;

import org.springframework.data.repository.Repository;
import pl.company.carservice.model.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends Repository<ServiceEntity, Long> {

    Long removeById(Long id);

    ServiceEntity save(ServiceEntity serviceEntity);

    boolean existsById(Long id);

    Optional<ServiceEntity> findById(Long id);

    List<ServiceEntity> findAll();

    void deleteById(Long id);

}
