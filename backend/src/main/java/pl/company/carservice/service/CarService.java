package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.company.carservice.model.Car;
import pl.company.carservice.repository.CarRepository;

import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Long addCar(Car car) {
        return this.carRepository.save(car).getId();
    }

    public Optional<Car> getCar(Long id) {
        return this.carRepository.findById(id);
    }

}
