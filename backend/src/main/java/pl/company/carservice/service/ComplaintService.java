package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.company.carservice.dto.ComplaintDto;
import pl.company.carservice.model.Complaint;
import pl.company.carservice.model.Task;
import pl.company.carservice.repository.ComplaintRepository;
import pl.company.carservice.repository.TaskRepository;

import javax.persistence.EntityManager;
import java.util.Map;

@Service
public class ComplaintService {

    private ComplaintRepository complaintRepository;
    private EntityManager entityManager;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, EntityManager entityManager) {
        this.complaintRepository = complaintRepository;
        this.entityManager = entityManager;
    }

    public ResponseEntity<?> addComplaint(@RequestBody ComplaintDto complaintDto) {
        Long taskId = complaintDto.taskId();
        String description = complaintDto.description();

        Task task = entityManager.getReference(Task.class, taskId);

        Complaint complaint = new Complaint(task, description);
        Long id = this.complaintRepository.save(complaint).getId();

        return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
    }
}
