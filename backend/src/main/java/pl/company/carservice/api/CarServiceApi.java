package pl.company.carservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.model.Car;
import pl.company.carservice.service.CarService;

@RestController
@RequestMapping("/api")
public class CarServiceApi {

    private CarService carService;

    @Autowired
    public CarServiceApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car")
    public Car getCar(@RequestParam Integer carId) {
        return new Car();
    }

}
