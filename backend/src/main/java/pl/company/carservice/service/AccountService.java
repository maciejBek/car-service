package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Customer;
import pl.company.carservice.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccount(Long id) {
        return this.accountRepository.findById(id);
    }

    public Long addAccount(Account account) {
        return this.accountRepository.save(account).getId();
    }

    public void deleteAccount(Long id) {
        this.accountRepository.deleteById(id);
    }

    public boolean ifExists(Long id) {
        return this.accountRepository.existsById(id);
    }
}
