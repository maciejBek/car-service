package pl.company.carservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.*;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.AccountKind;
import pl.company.carservice.model.Customer;
import pl.company.carservice.model.Employee;
import pl.company.carservice.repository.AccountRepository;
import pl.company.carservice.repository.CustomerRepository;
import pl.company.carservice.repository.EmployeeRepository;

import javax.persistence.Column;
import java.util.Map;

@Service
public class RegistrationService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public RegistrationService(AccountRepository accountRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
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

        String username = accountRegistrationDto.username();
        String password = accountRegistrationDto.password();
        String emailAddress = accountRegistrationDto.emailAddress();

        Account account = new Account(username, password, emailAddress);

        if (this.accountRepository.existsByUsername(account.getUsername())) {
            if (this.accountRepository.existsByEmailAddress(account.getEmailAddress())) {
                ErrorResponse errorResponse = new ErrorResponse("email-address-and-username-exist");
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
            }
            ErrorResponse errorResponse = new ErrorResponse("username-exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        if (this.accountRepository.existsByEmailAddress(account.getEmailAddress())) {
            ErrorResponse errorResponse = new ErrorResponse("email-address-exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

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

        //saving account to database
        Long addedAccountId = this.accountRepository.save(account).getId();

        // adding customer
        if (customer != null) {
            customer.setAccount(account);
            this.customerRepository.save(customer);
        }

        if (employee != null) {
            employee.setAccount(account);
            this.employeeRepository.save(employee);
        }

        return new ResponseEntity<>(Map.of("id", addedAccountId), HttpStatus.OK);
    }
}
