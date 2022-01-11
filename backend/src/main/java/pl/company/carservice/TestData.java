package pl.company.carservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.*;
import pl.company.carservice.dto.*;
import pl.company.carservice.model.*;
import pl.company.carservice.repository.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestData {

    private AccountRepository accountRepository;
    private AccountKindRepository accountKindRepository;
    private CarController carController;
    private CarRepository carRepository;
    private CustomerController customerController;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private PartRepository partRepository;
    private RegistrationController registrationController;
    private ServiceRepository serviceRepository;
    private TaskController taskController;

    @Autowired
    public TestData(AccountRepository accountRepository, AccountKindRepository accountKindRepository, CarController carController, CarRepository carRepository, CustomerController customerController, CustomerRepository customerRepository, OrderRepository orderRepository, PartRepository partRepository, RegistrationController registrationController, ServiceRepository serviceRepository, TaskController taskController) {
        this.accountRepository = accountRepository;
        this.accountKindRepository = accountKindRepository;
        this.carController = carController;
        this.carRepository = carRepository;
        this.customerController = customerController;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.partRepository = partRepository;
        this.registrationController = registrationController;
        this.serviceRepository = serviceRepository;
        this.taskController = taskController;
    }

    @EventListener(classes = ApplicationStartedEvent.class)
    public void addData(ApplicationStartedEvent event) {

        // Part 4x
        Part part1 = new Part("Tarcza sprzęgła", "0035672", "21 20 7 526 516", "BMW", "M3", "E92", 2011, 2);
        Part part2 = new Part("Tarcza hamulcowa, wentylowana, przednia, prawa", "0008672", "34 11 2 283 802", "BMW", "M3", " E92", 2011, 1);
        Part part3 = new Part("Panewka oporowa", "0155672", "11 21 1 706 831", "BMW", "Seria 3", " E36", 1997, 12);
        this.partRepository.save(part1);
        this.partRepository.save(part2);
        this.partRepository.save(part3);

        // Service 10x
        ServiceEntity service1 = new ServiceEntity("Wymiana klocków hamulcowych przód komplet", 1, 50, 100);
        ServiceEntity service2 = new ServiceEntity("Wymiana klocków hamulcowych tył komplet", 1, 50, 100);
        ServiceEntity service3 = new ServiceEntity("Regulacja zaworów silnikowych", 3, 150, 250);
        ServiceEntity service4 = new ServiceEntity("Wymiana oleju z filtrem", 1, 30, 50);
        ServiceEntity service5 = new ServiceEntity("Wymiana sprzęgła", 8, 400, 600);
        ServiceEntity service6 = new ServiceEntity("Wymiana rozrządu", 6, 250, 400);
        ServiceEntity service7 = new ServiceEntity("Wymiana uszczelki pod głowicą", 8, 350, 500);
        ServiceEntity service8 = new ServiceEntity("Wymiana amortyzatora przód 1 szt.", 2, 100, 150);
        ServiceEntity service9 = new ServiceEntity("Wymiana amortyzatora tył 1 szt.", 2, 75, 125);
        ServiceEntity service10 = new ServiceEntity("Wymiana kompletnego układu wydechowego", 4, 200, 300);
        this.serviceRepository.save(service1);
        this.serviceRepository.save(service2);
        this.serviceRepository.save(service3);
        this.serviceRepository.save(service4);
        this.serviceRepository.save(service5);
        this.serviceRepository.save(service6);
        this.serviceRepository.save(service7);
        this.serviceRepository.save(service8);
        this.serviceRepository.save(service9);
        this.serviceRepository.save(service10);

        // Order (2x)
        Order order1 = new Order(LocalDateTime.now(), LocalDateTime.now(), 1221);
        Order order2 = new Order(LocalDateTime.now(), LocalDateTime.now(), 2311);
        this.orderRepository.save(order1);
        this.orderRepository.save(order2);

        // AccountKind (all kinds)
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.ADMIN));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.CUSTOMER));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.EMPLOYEE));
        this.accountKindRepository.save(new AccountKind(AccountKind.PermissionLevel.SUPPLIER));

        // Admin Account
        AccountKind accountKind = new AccountKind(1L, AccountKind.PermissionLevel.ADMIN);
        Account account = new Account("admin", "12345", "admin@o2.pl", accountKind);
        this.accountRepository.save(account);

        // Address x3
        Address address1 = new Address("Bieszczadzka", "43", "Krosno", "38-400");
        Address address2 = new Address("Krakowska", "12", "Krosno", "38-400");
        Address address3 = new Address("Mackiewicza", "56", "Krakow", "31-214");

        // Customer Account
        AccountRegistrationDto accountRegistrationDto = new AccountRegistrationDto("kowalski123", "1234", "kowalski123@o2.pl");
        CustomerRegistrationDto customerRegistrationDto =  new CustomerRegistrationDto("Jan", "Kowalski", "882552389");
        Map<String, Object> accountCustomer = new HashMap<>();
        accountCustomer.put("account", accountRegistrationDto);
        accountCustomer.put("customer", customerRegistrationDto);
        accountCustomer.put("address", address1);
        this.registrationController.register(accountCustomer);

        // Customer Account
        AccountRegistrationDto accountRegistrationDto1 = new AccountRegistrationDto("nowak12", "1234", "nowak55@o2.pl");
        CustomerRegistrationDto customerRegistrationDto1 =  new CustomerRegistrationDto("Marian", "Nowak", "553273653");
        Map<String, Object> accountCustomer1 = new HashMap<>();
        accountCustomer1.put("account", accountRegistrationDto1);
        accountCustomer1.put("customer", customerRegistrationDto1);
        accountCustomer1.put("address", address2);
        this.registrationController.register(accountCustomer1);

        // Employee Account
        AccountRegistrationDto accountRegistrationDto2 = new AccountRegistrationDto("mariusz523", "1234", "mariusz32@o2.pl");
        EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto("Mariusz", "Kowal", "523723623", "91212321", "CD1234");
        Map<String, Object> accountEmployee = new HashMap<>();
        accountEmployee.put("account", accountRegistrationDto2);
        accountEmployee.put("employee", employeeRegistrationDto);
        accountEmployee.put("address", address3);
        this.registrationController.register(accountEmployee);

        // Car 2x
        Car car1 = new Car("BMW", "M3", 2012, "1HGBH41JXMN109186", "RKR 50123", 3999, "gasoline", 420, 400);
        car1.setCustomer(this.customerRepository.getById(1L));
        this.carRepository.save(car1);
        Car car2 = new Car("BMW", "M4", 2012, "1HGBH41JXAN109186", "RKR 50123", 3999, "gasoline", 420, 400);
        car2.setCustomer(this.customerRepository.getById(2L));
        this.carRepository.save(car2);

        // Task x15
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

        TaskAdditionDto taskAdditionDto1 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate1.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto2 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate2.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto3 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate3.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto4 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate4.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto5 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate5.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto6 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate6.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto7 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate7.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto8 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate8.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto9 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate9.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto10 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate10.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto11 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate11.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto12 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate12.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto13 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate13.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto14 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate14.toString(), "opis", "opis2");
        TaskAdditionDto taskAdditionDto15 = new TaskAdditionDto(1L, 1L, 1L, acceptationDate15.toString(), "opis", "opis2");

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
