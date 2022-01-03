package pl.company.carservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.*;
import pl.company.carservice.dto.*;
import pl.company.carservice.model.*;
import pl.company.carservice.repository.AccountKindRepository;
import pl.company.carservice.repository.CarRepository;
import pl.company.carservice.repository.CustomerRepository;
import pl.company.carservice.repository.TaskRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestData {

    private RegistrationController registrationController;
    private CustomerController customerController;
    private CustomerRepository customerRepository;
    private AccountKindRepository accountKindRepository;
    private TaskController taskController;
    private CarController carController;
    private CarRepository carRepository;

    @Autowired
    public TestData(RegistrationController registrationController, CustomerController customerController,
                    AccountKindRepository accountKindRepository, TaskController taskController, CarController carController, CarRepository carRepository,
                    CustomerRepository customerRepository) {
        this.registrationController = registrationController;
        this.customerController = customerController;
        this.accountKindRepository = accountKindRepository;
        this.taskController = taskController;
        this.carController = carController;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
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
//        Long customerId = this.registrationController.register(accountCustomer).;

        // TODO: delete below
//        AccountRegistrationDto accountRegistrationDto1 = new AccountRegistrationDto("nowak12", "1234", "nowak55@o2.pl");
//        CustomerRegistrationDto customerRegistrationDto1 =  new CustomerRegistrationDto("Marian", "Nowak", "553273653");
//        Map<String, Object> accountCustomer1 = new HashMap<>();
//        accountCustomer1.put("account", accountRegistrationDto1);
//        accountCustomer1.put("customer", customerRegistrationDto1);
//        this.registrationController.register(accountCustomer1);
//
//        // keep Employee Account data
//        AccountRegistrationDto accountRegistrationDto2 = new AccountRegistrationDto("mariusz523", "1234", "mariusz32@o2.pl");
//        EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto("Mariusz", "Kowal", "523723623", "91212321", "CD1234");
//        Map<String, Object> accountEmployee = new HashMap<>();
//        accountEmployee.put("account", accountRegistrationDto2);
//        accountEmployee.put("employee", employeeRegistrationDto);
//        this.registrationController.register(accountEmployee);

        // Car data
//        Customer customer = this.customerController.getCustomer(customerRegistrationDto.)
        Car car1 = new Car("BMW", "M3", 2012, "1HGBH41JXMN109186", "RKR 50123", 3999, "gasoline", 420, 400);
        car1.setCustomer(this.customerRepository.getById(1L));
        this.carRepository.save(car1);
        Car car2 = new Car("BMW", "M4", 2012, "1HGBH41JXAN109186", "RKR 50123", 3999, "gasoline", 420, 400);
        car2.setCustomer(this.customerRepository.getById(1L));
        this.carRepository.save(car2);


        // Task data
        LocalDateTime acceptationDate1 = LocalDateTime.of(2021, 12, 8, 10, 45, 20, 3353);
        LocalDateTime acceptationDate2 = LocalDateTime.of(2021, 12, 9, 12, 45, 20, 3353);
        LocalDateTime acceptationDate3 = LocalDateTime.of(2021, 12, 10, 15, 45, 20, 3353);
        LocalDateTime acceptationDate4 = LocalDateTime.of(2021, 12, 11, 18, 45, 20, 3353);
        LocalDateTime acceptationDate5 = LocalDateTime.of(2021, 12, 12, 11, 45, 20, 3353);
        LocalDateTime acceptationDate6 = LocalDateTime.of(2021, 12, 13, 11, 45, 20, 3353);
        LocalDateTime acceptationDate7 = LocalDateTime.of(2021, 12, 15, 15, 45, 20, 3353);
        LocalDateTime acceptationDate8 = LocalDateTime.of(2021, 12, 17, 13, 45, 20, 3353);
        LocalDateTime acceptationDate9 = LocalDateTime.of(2021, 12, 20, 14, 45, 20, 3353);
        LocalDateTime acceptationDate10 = LocalDateTime.of(2021, 12, 21, 16, 45, 20, 3353);
        LocalDateTime acceptationDate11 = LocalDateTime.of(2021, 12, 23, 17, 45, 20, 3353);
        LocalDateTime acceptationDate12 = LocalDateTime.of(2021, 12, 25, 8, 45, 20, 3353);
        LocalDateTime acceptationDate13 = LocalDateTime.of(2021, 12, 27, 9, 45, 20, 3353);
        LocalDateTime acceptationDate14 = LocalDateTime.of(2021, 12, 29, 8, 45, 20, 3353);
        LocalDateTime acceptationDate15 = LocalDateTime.of(2021, 12, 30, 10, 45, 20, 3353);
        LocalDateTime completionDate = LocalDateTime.of(2022, 1, 2, 13, 45, 20, 3353);
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
