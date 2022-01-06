package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Customer;
import pl.company.carservice.model.ServiceEntity;
import pl.company.carservice.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceService {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ResponseEntity<?> getService(Long id) {
        Optional<ServiceEntity> fetchedCar = this.serviceRepository.findById(id);

        if (fetchedCar.isPresent()) {
            ServiceEntity service = fetchedCar.get();
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "service-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getServices() {
        List<ServiceEntity> services = this.serviceRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addService(ServiceEntity service) {
        Long addedServiceId = this.serviceRepository.save(service).getId();

        return new ResponseEntity<>(Map.of("id", addedServiceId), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteService(Long id) {
        if (this.serviceRepository.existsById(id)) {
            this.serviceRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "service-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
