package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.StringToJson;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Customer;
import pl.company.carservice.service.CarService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars/{id}", produces = "application/json")
    public ResponseEntity<?> getCar(@PathVariable Long id) {
        return this.carService.getCar(id);
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody CarCustomerIdDto carCustomerIdDto) {
        return this.carService.addCar(carCustomerIdDto);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        return this.carService.deleteCar(id);
    }
}