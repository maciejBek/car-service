package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.CustomerIdNameSurnameDto;
import pl.company.carservice.model.Customer;
import pl.company.carservice.model.ServiceEntity;
import pl.company.carservice.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

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

    public ResponseEntity<?> getCustomers() {
        List<CustomerIdNameSurnameDto> services = this.customerRepository.findAllDtoBy();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
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
}
