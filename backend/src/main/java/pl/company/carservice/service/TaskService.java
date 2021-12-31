package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.AccountLoginDto;
import pl.company.carservice.dto.TaskAdditionDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.Task;
import pl.company.carservice.repository.AccountRepository;
import pl.company.carservice.repository.TaskRepository;

import java.sql.Date;
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

    //TODO: test this function
    public ResponseEntity<?> addTask(TaskAdditionDto taskAdditionDto) {


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
