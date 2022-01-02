package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Customer;
import pl.company.carservice.repository.CarRepository;
import pl.company.carservice.repository.CustomerRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CarService(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
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

    public ResponseEntity<?> addCar(CarCustomerIdDto carCustomerIdDto) {
        Long customerId = carCustomerIdDto.customerId();

        if (customerId == null) {
            ErrorResponse errorResponse = new ErrorResponse("invalid-data");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

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
