package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.TaskAdditionDto;
import pl.company.carservice.service.TaskService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@RequestBody TaskAdditionDto taskAdditionDto) {
        return this.taskService.addTask(taskAdditionDto);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return this.taskService.deleteTask(id);
    }

    //TODO: get tasks with pagination (PATCH METHOD)
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> getTasks() {
        return new ResponseEntity<>(HttpStatus.OK);
    };

}
