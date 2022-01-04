package pl.company.carservice.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.AccountCustomerDto;
import pl.company.carservice.dto.AccountEmployeeDto;
import pl.company.carservice.dto.AccountRegistrationDto;
import pl.company.carservice.dto.AccountUserDtoInterface;
import pl.company.carservice.service.RegistrationService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountRegistrationDto accountRegistrationDto) {
        return this.registrationService.register(accountRegistrationDto);
    }
}