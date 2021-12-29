package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.model.Customer;
import pl.company.carservice.repository.CustomerRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> getCustomer(Long id) {
        Optional<Customer> fetchedCustomer = this.customerRepository.findById(id);

        if (fetchedCustomer.isPresent()) {
            return new ResponseEntity<>(fetchedCustomer.get(), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("customer-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);

        if (this.customerRepository.existsById(id)) {
            this.customerRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("customer-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public boolean ifExists(Long id) {
        return this.customerRepository.existsById(id);
    }

}
