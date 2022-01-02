package pl.company.carservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.AccountController;
import pl.company.carservice.controller.CustomerController;
import pl.company.carservice.controller.RegistrationController;
import pl.company.carservice.controller.TaskController;
import pl.company.carservice.dto.*;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.AccountKind;
import pl.company.carservice.model.Customer;
import pl.company.carservice.model.Employee;
import pl.company.carservice.repository.AccountKindRepository;
import pl.company.carservice.repository.TaskRepository;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestData {

    private RegistrationController registrationController;
    private CustomerController customerController;
    private AccountKindRepository accountKindRepository;
    private TaskController taskController;

    @Autowired
    public TestData(RegistrationController registrationController, CustomerController customerController,
                    AccountKindRepository accountKindRepository, TaskController taskController) {
        this.registrationController = registrationController;
        this.customerController = customerController;
        this.accountKindRepository = accountKindRepository;
        this.taskController = taskController;
    }

    @EventListener(classes = ApplicationStartedEvent.class)
    public void addData(ApplicationStartedEvent event) {
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.ADMIN));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.CUSTOMER));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.EMPLOYEE));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.SUPPLIER));

        // TODO: delete below
        AccountRegistrationDto accountRegistrationDto = new AccountRegistrationDto("kowalski123", "1234", "kowalski123@o2.pl");
        CustomerRegistrationDto customerRegistrationDto =  new CustomerRegistrationDto("Jan", "Kowalski", "882552389");
        Map<String, Object> accountCustomer = new HashMap<>();
        accountCustomer.put("account", accountRegistrationDto);
        accountCustomer.put("customer", customerRegistrationDto);
        this.registrationController.register(accountCustomer);

        // TODO: delete below
        AccountRegistrationDto accountRegistrationDto1 = new AccountRegistrationDto("nowak12", "1234", "nowak55@o2.pl");
        CustomerRegistrationDto customerRegistrationDto1 =  new CustomerRegistrationDto("Marian", "Nowak", "553273653");
        Map<String, Object> accountCustomer1 = new HashMap<>();
        accountCustomer1.put("account", accountRegistrationDto1);
        accountCustomer1.put("customer", customerRegistrationDto1);
        this.registrationController.register(accountCustomer1);

        // keep Employee Account data
        AccountRegistrationDto accountRegistrationDto2 = new AccountRegistrationDto("mariusz523", "1234", "mariusz32@o2.pl");
        EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto("Mariusz", "Kowal", "523723623", "91212321", "CD1234");
        Map<String, Object> accountEmployee = new HashMap<>();
        accountEmployee.put("account", accountRegistrationDto2);
        accountEmployee.put("employee", employeeRegistrationDto);
        this.registrationController.register(accountEmployee);

        // Tasks data
        Date acceptationDate1 = new Date(1641135266);
        Date acceptationDate2 = new Date(1641155266);
        Date acceptationDate3 = new Date(1641455266);
        Date acceptationDate4 = new Date(1641155266);
        Date acceptationDate5 = new Date(1641155266);
        Date acceptationDate6 = new Date(1641155266);
        Date acceptationDate7 = new Date(1641155266);
        Date acceptationDate8 = new Date(1641155266);
        Date acceptationDate9 = new Date(1641155266);
        Date acceptationDate10 = new Date(1641155266);
        Date acceptationDate11 = new Date(1641155266);
        Date acceptationDate12 = new Date(1641155266);
        Date acceptationDate13 = new Date(1641155266);
        Date acceptationDate14 = new Date(1641155266);
        Date acceptationDate15 = new Date(1641155266);
        Date completionDate = new Date(1641155266);
        TaskAdditionDto taskAdditionDto1 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate1, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto2 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate2, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto3 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate3, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto4 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate4, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto5 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate5, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto6 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate6, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto7 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate7, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto8 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate8, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto9 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate9, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto10 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate10, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto11 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate11, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto12 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate12, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto13 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate13, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto14 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate14, completionDate, "opis", "opis2");
        TaskAdditionDto taskAdditionDto15 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate15, completionDate, "opis", "opis2");

        this.taskController.addTask(taskAdditionDto1);
        this.taskController.addTask(taskAdditionDto2);
        this.taskController.addTask(taskAdditionDto3);
        this.taskController.addTask(taskAdditionDto4);
        this.taskController.addTask(taskAdditionDto5);
        this.taskController.addTask(taskAdditionDto6);
        this.taskController.addTask(taskAdditionDto7);
        this.taskController.addTask(taskAdditionDto8);
        this.taskController.addTask(taskAdditionDto9);
        this.taskController.addTask(taskAdditionDto10);
        this.taskController.addTask(taskAdditionDto11);
        this.taskController.addTask(taskAdditionDto12);
        this.taskController.addTask(taskAdditionDto13);
        this.taskController.addTask(taskAdditionDto14);
        this.taskController.addTask(taskAdditionDto15);
    }
}
