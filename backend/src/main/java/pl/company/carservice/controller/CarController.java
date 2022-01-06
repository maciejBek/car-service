package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.service.CarService;

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

    @GetMapping(value = "/cars", produces = "application/json")
    public ResponseEntity<?> getCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize,
            @RequestParam(defaultValue = "brand") String sortBy,
            @RequestParam(defaultValue = "") Long customerId) {

        return this.carService.getCars(pageNo, pageSize, sortBy, customerId);
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