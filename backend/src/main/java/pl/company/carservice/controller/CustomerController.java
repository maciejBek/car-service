package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.model.Customer;
import pl.company.carservice.service.CustomerService;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        return this.customerService.getCustomer(id);
    }

    @GetMapping(value = "/customers", produces = "application/json")
    public ResponseEntity<?> getCustomers() {
        return this.customerService.getCustomers();
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return this.customerService.deleteCustomer(id);
    }

}
