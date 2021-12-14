package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.StringToJson;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Customer;
import pl.company.carservice.service.AccountService;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        Optional<Account> fetchedAccount = accountService.getAccount(id);

        if (fetchedAccount.isPresent()) {
            Account account = fetchedAccount.get();
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "Account with specified id does not exist!");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accounts")
    public Map<String, Long> addCar(@RequestBody Account account) {
        return Map.of("id", accountService.addAccount(account));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        if (accountService.ifExists(id)) {
            accountService.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } else {
            String errorResponse = StringToJson.parse("error", "Account with specified id does not exist!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
