package pl.company.carservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.AccountController;
import pl.company.carservice.controller.CustomerController;
import pl.company.carservice.controller.RegistrationController;
import pl.company.carservice.dto.AccountCustomerDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.AccountKind;
import pl.company.carservice.model.Customer;
import pl.company.carservice.repository.AccountKindRepository;

@Service
public class TestData {

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private AccountKindRepository accountKindRepository;


    @EventListener(classes = ApplicationStartedEvent.class)
    public void addTestData(ApplicationStartedEvent event) {
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.ADMIN));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.CUSTOMER));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.EMPLOYEE));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.SUPPLIER));

        Account account = new Account("kowalski123", "1234", "kowalski123@o2.pl");
        Customer customer =  new Customer("Jan", "Kowalski", "882552389");
        AccountCustomerDto accountCustomerDto = new AccountCustomerDto(account, customer);
        this.registrationController.register(accountCustomerDto);

        Account account1 = new Account("nowak12", "1234", "nowak55@o2.pl");
        Customer customer1 =  new Customer("Marian", "Nowak", "553273653");
        AccountCustomerDto accountCustomerDto1 = new AccountCustomerDto(account1, customer1);
        this.registrationController.register(accountCustomerDto1);




    }
}
