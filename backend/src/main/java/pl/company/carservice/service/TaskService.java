package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.CarCustomerServiceTaskDto;
import pl.company.carservice.dto.CompletionDateDto;
import pl.company.carservice.dto.TaskAdditionDto;
import pl.company.carservice.model.*;
import pl.company.carservice.repository.AccountRepository;
import pl.company.carservice.repository.CustomerRepository;
import pl.company.carservice.repository.TaskRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private CustomerRepository customerRepository;
    private EntityManager entityManager;

    @Autowired
    public TaskService(TaskRepository taskRepository, CustomerRepository customerRepository, EntityManager entityManager) {
        this.taskRepository = taskRepository;
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
    }

    public ResponseEntity<?> getTask(Long id) {
        Optional<Task> fetchedAccount = this.taskRepository.findById(id);
        if (fetchedAccount.isPresent()) {
            return new ResponseEntity<>(fetchedAccount.get(), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("task-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getTasks(Integer pageNo, Integer pageSize, String sortBy) {
        System.out.println("sortBy:" + sortBy + ".");
        if (pageSize > 100 || !("acceptanceDate".equals(sortBy) || "completionDate".equals(sortBy) || "id".equals(sortBy))) {
            ErrorResponse errorResponse = new ErrorResponse("invalid-parameter");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<CarCustomerServiceTaskDto> pagedResult = taskRepository.findAllDto(paging);
        if (pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getTasksByAccountId(@PathVariable Long accountId) {
        Customer customer = this.customerRepository.findByAccount_Id(accountId).get();
        Long customerId = customer.getId();
        List<Task> tasks = this.taskRepository.findAllByCustomerId(customerId);

        System.out.println("task: " + tasks.size());

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    //TODO: validation
    public ResponseEntity<?> addTask(TaskAdditionDto taskAdditionDto) {
        Long serviceId = taskAdditionDto.serviceId();
        Long carId = taskAdditionDto.carId();
        Long customerId = taskAdditionDto.customerId();

        // incompatible data formats javascript <-> java ('Z' at the end of data)
        // deleting 'Z'
        String acceptanceDateString = taskAdditionDto.acceptanceDate();
        StringBuffer stringBuffer = new StringBuffer(acceptanceDateString);
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        acceptanceDateString = stringBuffer.toString();

        LocalDateTime acceptanceDate = LocalDateTime.parse(acceptanceDateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String serviceDescription = taskAdditionDto.serviceDescription();
        String problemDescription = taskAdditionDto.problemDescription();

        ServiceEntity service = entityManager.getReference(ServiceEntity.class, serviceId);
        Car car = entityManager.getReference(Car.class, carId);
        Customer customer = entityManager.getReference(Customer.class, customerId);

        Task task = new Task(service, car, customer, acceptanceDate, serviceDescription, problemDescription);
        this.taskRepository.save(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> markAsCompleted(Long id, CompletionDateDto completionDateDto) {
        // incompatible data formats javascript <-> java ('Z' at the end of data)
        // deleting 'Z'
        String completionDate = completionDateDto.completionDate();
        StringBuffer stringBuffer = new StringBuffer(completionDate);
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        completionDate = stringBuffer.toString();

        LocalDateTime completionDateLocalDateTime = LocalDateTime.parse(completionDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        this.taskRepository.markAsCompleted(id, completionDateLocalDateTime);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTask(Long id) {
        if (this.taskRepository.existsById(id)) {
            this.taskRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("task-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
