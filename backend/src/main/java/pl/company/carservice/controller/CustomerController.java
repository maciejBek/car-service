package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.StringToJson;
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
        return customerService.getCustomer(id);
    }

    @PostMapping("/customers")
    public Map<String, Long> addCustomer(@RequestBody Customer customer) {
        return Map.of("id", customerService.addCustomer(customer));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        if (customerService.ifExists(id)) {
            customerService.deleteCar(id);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } else {
            String errorResponse = StringToJson.parse("error", "Customer with specified id does not exist!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
