package pl.company.carservice.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.AccountCustomerDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.AccountKind;
import pl.company.carservice.model.Customer;
import pl.company.carservice.repository.AccountRepository;
import pl.company.carservice.repository.CustomerRepository;

import java.util.Map;

@Service
public class RegistrationService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public RegistrationService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> register(AccountCustomerDto accountCustomerDto) {
        // add account
        Account account = accountCustomerDto.account();
        Customer customer = accountCustomerDto.customer();
        System.out.println("username: " + account.getUsername());
        System.out.println("name: " + customer.getName());
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
        // TODO: make DTO
        AccountKind accountKind = new AccountKind(2L, AccountKind.PermissionLevel.CUSTOMER);
        account.setAccountKind(accountKind);
        Account addedAccount = this.accountRepository.save(account);

        // add customer
        customer.setAccount(account);
        this.customerRepository.save(customer);

        return new ResponseEntity<>(Map.of("id", addedAccount.getId()), HttpStatus.OK);
    }
}
