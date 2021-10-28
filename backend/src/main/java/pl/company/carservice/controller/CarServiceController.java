package pl.company.carservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarServiceController {

    @GetMapping("/api/get")
    public String getFunction() {
        return "hello hello";
    }
}
