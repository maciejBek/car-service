package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.AccountRegistrationDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.repository.AccountRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<?> getAccount(Long id) {
        Optional<Account> fetchedAccount = this.accountRepository.findById(id);
        if (fetchedAccount.isPresent()) {
            return new ResponseEntity<>(fetchedAccount.get(), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("account-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("account-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> login(AccountRegistrationDto accountRegistrationDto) {
        String password = accountRegistrationDto.password();
        String retypePassword = accountRegistrationDto.retypePassword();

        if (password.equals(retypePassword)) {
            String username = accountRegistrationDto.username();
            if (this.accountRepository.existsByUsernameIsContainingAndPassword(username, password)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("invalid-data");
                return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse("different-passwords");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> register(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            if (accountRepository.existsByEmailAddress(account.getEmailAddress())) {
                ErrorResponse errorResponse = new ErrorResponse("email-address-and-username-exist");
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
            }
            ErrorResponse errorResponse = new ErrorResponse("username-exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        if (accountRepository.existsByEmailAddress(account.getEmailAddress())) {
            ErrorResponse errorResponse = new ErrorResponse("email-address-exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        // TODO: make DTO
        Account addedAccount = this.accountRepository.save(account);
        return new ResponseEntity<>(Map.of("id", addedAccount.getId()), HttpStatus.OK);
    }
}
