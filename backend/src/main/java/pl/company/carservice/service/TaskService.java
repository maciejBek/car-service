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

import java.util.ArrayList;
import java.util.List;
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

    public ResponseEntity<?> getTasks() {
//        return this.taskService.getTasks().stream()
//                .map(task -> new TaskDto(task.getField(), ...))
//	.collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Task> getTasks(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Task> pagedResult = taskRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Task>();
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
