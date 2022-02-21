package pl.company.carservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.*;
import pl.company.carservice.model.*;
import pl.company.carservice.repository.AccountRepository;
import pl.company.carservice.repository.AddressRepository;
import pl.company.carservice.repository.CustomerRepository;
import pl.company.carservice.repository.EmployeeRepository;

import javax.persistence.Column;
import java.util.Map;

@Service
public class RegistrationService {

    private AddressRepository addressRepository;
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    private AccountService accountService;

    public RegistrationService(AddressRepository addressRepository, AccountRepository accountRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, AccountService accountService) {
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.accountService = accountService;
    }


    public ResponseEntity<?> register(Map<String, Object> accountAndUser) {
//    public ResponseEntity<?> register(AccountRegistrationDto accountRegistrationDto) {
        // add account
            // mapping object
            // TODO: use mapper MapStruct

//        String username = accountRegistrationDto.username();
//        String password = accountRegistrationDto.password();
//        String emailAddress = accountRegistrationDto.emailAddress();
//        AccountKind accountKind = new AccountKind(AccountKind.PermissionLevel.CUSTOMER);
//        Account account = new Account(username, password, emailAddress, accountKind);
//
//
//        Long accountId = this.accountRepository.save(account).getId();
//
//        return new ResponseEntity<>(Map.of("accountId", accountId), HttpStatus.OK);

        ObjectMapper objectMapper = new ObjectMapper();
        AccountRegistrationDto accountRegistrationDto = objectMapper.convertValue(accountAndUser.get("account"), AccountRegistrationDto.class);

        if (accountRegistrationDto == null) {
            ErrorResponse errorResponse = new ErrorResponse("empty-data");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // check if account exists
        ResponseEntity<?> responseEntity = accountService.existsAccount(accountRegistrationDto);
        // if there is an error
        if (responseEntity.hasBody()) {
            ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        String username = accountRegistrationDto.username();
        String password = accountRegistrationDto.password();
        String emailAddress = accountRegistrationDto.emailAddress();
        Account account = new Account(username, password, emailAddress);

        // checking if user is customer or employee
        Customer customer = null;
        Employee employee =  null;
        AccountKind accountKind = null;
        if (accountAndUser.get("customer") != null) {
            accountKind = new AccountKind(2L, AccountKind.PermissionLevel.CUSTOMER);
            // TODO: use mapper MapStruct
            CustomerRegistrationDto customerRegistrationDto = objectMapper.convertValue(accountAndUser.get("customer"), CustomerRegistrationDto.class);
            String name = customerRegistrationDto.name();
            String surname = customerRegistrationDto.surname();
            String phoneNumber = customerRegistrationDto.phoneNumber();

            customer = new Customer(name, surname, phoneNumber);
        }
        if (accountAndUser.get("employee") != null) {
            accountKind = new AccountKind(3L, AccountKind.PermissionLevel.EMPLOYEE);
            EmployeeRegistrationDto employeeRegistrationDto = objectMapper.convertValue(accountAndUser.get("employee"), EmployeeRegistrationDto.class);
            String name = employeeRegistrationDto.name();
            String surname = employeeRegistrationDto.surname();
            String phoneNumber = employeeRegistrationDto.phoneNumber();
            String pesel = employeeRegistrationDto.pesel();
            String idNumber = employeeRegistrationDto.idNumber();

            employee = new Employee(name, surname, phoneNumber, pesel, idNumber);
        }

        account.setAccountKind(accountKind);

        // save address to database
        Address address = objectMapper.convertValue(accountAndUser.get("address"), Address.class);
        address = this.addressRepository.save(address);

        //saving account to database
        Long addedAccountId = this.accountRepository.save(account).getId();

        // adding customer
        if (customer != null) {
            customer.setAccount(account);
            customer.setAddress(address);
            this.customerRepository.save(customer);
        }

        // or adding employee
        if (employee != null) {
            employee.setAccount(account);
            employee.setAddress(address);
            this.employeeRepository.save(employee);
        }

        return new ResponseEntity<>(Map.of("id", addedAccountId), HttpStatus.OK);
    }
}
