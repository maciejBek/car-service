package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.AccountLoginDto;
import pl.company.carservice.dto.AccountRegistrationDto;
import pl.company.carservice.service.AccountService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // TODO: add DTO to get data about account without password
    @GetMapping(value = "/accounts/{id}", produces = "application/json")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> existsAccount (@RequestBody AccountRegistrationDto accountRegistrationDto) {
        return this.accountService.existsAccount(accountRegistrationDto);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        return this.accountService.deleteAccount(id);
    }

    @PostMapping("/accounts/login")
    public ResponseEntity<?> login(@RequestBody AccountLoginDto accountLoginDto) {
        return this.accountService.login(accountLoginDto);
    }
}
