package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.dto.CarIdBrandModelDto;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Customer;
import pl.company.carservice.repository.CarRepository;
import pl.company.carservice.repository.CustomerRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private EntityManager entityManager;

    public CarService(CarRepository carRepository, CustomerRepository customerRepository, EntityManager entityManager) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
    }

    public ResponseEntity<?> getCar(Long id) {
        Optional<Car> fetchedCar = this.carRepository.findById(id);

        if (fetchedCar.isPresent()) {
            Car car = fetchedCar.get();
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "car-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getCars(Integer pageNo, Integer pageSize, String sortBy, Long customerId) {
        if (pageSize > 200 || !("id".equals(sortBy) || "brand".equals(sortBy) || "model".equals(sortBy))) {
            ErrorResponse errorResponse = new ErrorResponse("invalid-parameter");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<CarIdBrandModelDto> pagedResult = null;
        if (customerId != null) {
            pagedResult = this.carRepository.findAllById(customerId, paging);
        } else {
            pagedResult = this.carRepository.findAllDtoBy(paging);
        }

        if (pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    // TODO: chech optional
    public ResponseEntity<?> getCarsByAccountId(Long accountId) {
        Optional<Customer> customer = this.customerRepository.findByAccount_Id(accountId);
        if (customer.isPresent()) {
            Long customerId = customer.get().getId();

            List<CarIdBrandModelDto> cars = this.carRepository.findAllDtoByCustomerId(customerId);

            return new ResponseEntity<>(cars, HttpStatus.OK);
        }

        ErrorResponse errorResponse = new ErrorResponse("does-not-exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addCar(CarCustomerIdDto carCustomerIdDto) {
        Long customerId = carCustomerIdDto.customerId();

        if (customerId == null) {
            ErrorResponse errorResponse = new ErrorResponse("invalid-data");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // TODO: entityManager.getReference(ServiceEntity.class, serviceId);
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isPresent()) {
            String brand = carCustomerIdDto.brand();
            String model = carCustomerIdDto.model();
            Integer year = carCustomerIdDto.year();
            String vinNumber = carCustomerIdDto.vinNumber();
            String registrationNumber = carCustomerIdDto.registrationNumber();
            Integer capacity = carCustomerIdDto.capacity();
            String fuelType = carCustomerIdDto.fuelType();
            Integer power = carCustomerIdDto.power();
            Integer tourqe = carCustomerIdDto.tourqe();
            Car car = new Car(brand, model, year, vinNumber, registrationNumber, capacity, fuelType, power, tourqe);
            car.setCustomer(customer.get());

            Long addedCarId = this.carRepository.save(car).getId();

            return new ResponseEntity<>(Map.of("id", addedCarId), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("customer-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteCar(Long id) {
        if (this.carRepository.existsById(id)) {
            this.carRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "car-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
