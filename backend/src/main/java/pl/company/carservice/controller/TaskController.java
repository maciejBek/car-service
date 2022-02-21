package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.CompletionDateDto;
import pl.company.carservice.dto.TaskAdditionByAccountIdDto;
import pl.company.carservice.dto.TaskAdditionDto;
import pl.company.carservice.service.TaskService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    //TODO: get tasks with pagination
    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<?> getTasks(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return this.taskService.getTasks(pageNo, pageSize, sortBy);
    }

    @GetMapping(value = "/tasks/customer/{accountId}", produces = "application/json")
    public ResponseEntity<?> getTasksByAccountId(@PathVariable Long accountId) {
        return taskService.getTasksByAccountId(accountId);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> addTaskByAccountId(@RequestBody TaskAdditionByAccountIdDto taskAdditionByAccountIdDto) {
        return this.taskService.addTaskByAccountId(taskAdditionByAccountIdDto);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<?> markAsCompleted(@PathVariable Long id, @RequestBody CompletionDateDto completionDateDto) {
        return this.taskService.markAsCompleted(id, completionDateDto);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return this.taskService.deleteTask(id);
    }

}
