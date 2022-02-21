package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.company.carservice.model.AccountKind;
import pl.company.carservice.repository.AccountKindRepository;
import pl.company.carservice.model.AccountKind.PermissionLevel;

@Service
public class AccountKindService {

    private AccountKindRepository accountKindRepository;

    public AccountKindService(AccountKindRepository accountKindRepository) {
        this.accountKindRepository = accountKindRepository;
    }

    //TODO: uncomment at the end
//    @EventListener(classes = ApplicationStartedEvent.class)
//    public void addAcountKindData(ApplicationStartedEvent event) {
//        this.accountKindRepository.save(new AccountKind(PermissionLevel.ADMIN));
//        this.accountKindRepository.save(new AccountKind(PermissionLevel.CUSTOMER));
//        this.accountKindRepository.save(new AccountKind(PermissionLevel.EMPLOYEE));
//        this.accountKindRepository.save(new AccountKind(PermissionLevel.SUPPLIER));
//    }
}
