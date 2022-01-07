package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.AccountLoginDto;
import pl.company.carservice.dto.AccountRegistrationDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.AccountKind;
import pl.company.carservice.repository.AccountRepository;

import java.util.Locale;
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

    public ResponseEntity<?> existsAccount (@RequestBody AccountRegistrationDto accountRegistrationDto) {
        if (this.accountRepository.existsByUsername(accountRegistrationDto.username())) {
            if (this.accountRepository.existsByEmailAddress(accountRegistrationDto.emailAddress())) {
                ErrorResponse errorResponse = new ErrorResponse("email-address-and-username-exist");
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
            }
            ErrorResponse errorResponse = new ErrorResponse("username-exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        if (this.accountRepository.existsByEmailAddress(accountRegistrationDto.emailAddress())) {
            ErrorResponse errorResponse = new ErrorResponse("email-address-exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAccount(Long id) {
        if (this.accountRepository.existsById(id)) {
            this.accountRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("account-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> login(AccountLoginDto accountLoginDto) {
        String username = accountLoginDto.username();
        String password = accountLoginDto.password();

        Optional<Account> accountOptional = this.accountRepository.findByUsernameAndPassword(username, password);
        boolean isCorrectData = accountOptional.isPresent();
        if (isCorrectData) {
            //TODO: JWT authentication token
            AccountKind accountKind = accountOptional.get().getAccountKind();
            String accountKindString = accountKind.getPermissionLevel().toString().toLowerCase(Locale.ROOT);

            return new ResponseEntity<>(Map.of("accountKind", accountKindString), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("invalid-data");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
