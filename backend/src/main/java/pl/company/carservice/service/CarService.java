package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Customer;
import pl.company.carservice.repository.CarRepository;

import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<Car> getCar(Long id) {
        return this.carRepository.findById(id);
    }

    public Long addCar(Car car) {
        Customer customer = new Customer("Mariusz", "Kowalski", "543723812");
        car.setCustomer(customer);
        return this.carRepository.save(car).getId();
    }

    public Long addCar(Car car, Customer customer) {
        car.setCustomer(customer);
        return this.carRepository.save(car).getId();
    }

    public void deleteCar(Long id) {
        this.carRepository.deleteById(id);
    }

    public boolean ifExists(Long id) {
        return this.carRepository.existsById(id);
    }
}
