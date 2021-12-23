package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.model.Account;
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

    // TODO: add DTO to get data about account without password
    @GetMapping(value = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        Optional<Account> fetchedAccount = accountService.getAccount(id);

        if (fetchedAccount.isPresent()) {
            Account account = fetchedAccount.get();
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Account with specified id does not exist!");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        if (accountService.ifExistsByUsername(account.getUsername())) {
            if (accountService.ifExistsByEmailAddress(account.getEmailAddress())) {
                ErrorResponse errorResponse = new ErrorResponse("emailAddress and username exists");
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
            }
            ErrorResponse errorResponse = new ErrorResponse("username exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        if (accountService.ifExistsByEmailAddress(account.getEmailAddress())) {
            ErrorResponse errorResponse = new ErrorResponse("emailAddress exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//            return new ResponseEntity<>(Map.of("error", "emailAddress exists"), HttpStatus.CONFLICT);
        }
        // TODO: make DTO
        return new ResponseEntity<>(Map.of("id", accountService.addAccount(account)), HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        if (accountService.ifExistsById(id)) {
            accountService.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Account with specified id does not exist!");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
