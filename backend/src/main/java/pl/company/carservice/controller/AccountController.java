package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.AccountRegistrationDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.service.AccountService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // TODO: add DTO to get data about account without password
    @GetMapping(value = "/accounts/{id}", produces = "application/json")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        return this.accountService.deleteAccount(id);
    }

    @PostMapping("/accounts/login")
    public ResponseEntity<?> login(@RequestBody AccountRegistrationDto accountRegistrationDto) {
        return this.accountService.login(accountRegistrationDto);
    }

    @PostMapping("/accounts/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        return this.accountService.register(account);
    }

}
