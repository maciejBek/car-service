package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.ComplaintDto;
import pl.company.carservice.dto.CompletionDateDto;
import pl.company.carservice.dto.TaskAdditionDto;
import pl.company.carservice.model.Complaint;
import pl.company.carservice.service.ComplaintService;
import pl.company.carservice.service.TaskService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ComplaintController {

    private ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/complaints")
    public ResponseEntity<?> addComplaint(@RequestBody ComplaintDto complaintDto) {
        return this.complaintService.addComplaint(complaintDto);
    }

}