package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.TaskAdditionDto;
import pl.company.carservice.model.Task;
import pl.company.carservice.repository.TaskRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
        if (pageSize > 100 || !("acceptationDate".equals(sortBy) || "completionDate".equals(sortBy)) || "id".equals(sortBy) || "".equals(sortBy)) {
            ErrorResponse errorResponse = new ErrorResponse("invalid-parameter");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Task> pagedResult = taskRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    //TODO: test this function
    public ResponseEntity<?> addTask(TaskAdditionDto taskAdditionDto) {
        Long serviceId = taskAdditionDto.serviceId();
        Long carId = taskAdditionDto.carId();
        Long customerId = taskAdditionDto.customerId();
        LocalDateTime acceptationDate = taskAdditionDto.acceptationDate();
        LocalDateTime completionDateDate = taskAdditionDto.completionDate();
        String serviceDescription = taskAdditionDto.serviceDescription();
        String problemDescription = taskAdditionDto.problemDescription();

        Task task = new Task(acceptationDate, completionDateDate, serviceDescription, problemDescription);

        this.taskRepository.save(task);

        // TODO: sprawdzic czy nie ma nulli, ale najpierw dac not null na kolumny entity i sprawdzic jak baza dostarcza blad do springboota, pewnie trzeba bedzie na try{} zrobic
//        if (serviceId == null || carId == null || customerId == null ) {
//            ErrorResponse errorResponse = new ErrorResponse("invalid-data");
//            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//        }

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
