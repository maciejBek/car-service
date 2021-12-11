package pl.company.carservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.StringToJson;
import pl.company.carservice.model.Car;
import pl.company.carservice.service.CarService;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CarServiceApi {

    private CarService carService;

    @Autowired
    public CarServiceApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getCar(@PathVariable Long id) {
        Optional<Car> fetchedCar = carService.getCar(id);

        if (fetchedCar.isPresent()) {
            return fetchedCar.get();
        } else {
            String errorResponse = StringToJson.parse("error", "Car with specified id does not exist!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping("/cars")
    public Map<String, Long> addCar(@RequestBody Car car) {
        return Map.of("id", carService.addCar(car));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        if (carService.ifExists(id)) {
            carService.deleteCar(id);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } else {
            String errorResponse = StringToJson.parse("error", "Car with specified id does not exist!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}