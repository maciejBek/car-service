package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ServiceEntity service;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime acceptanceDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime completionDate;

    private String serviceDescription;

    private String problemDescription;

    public Task(LocalDateTime acceptanceDate, String serviceDescription, String problemDescription) {
        this.acceptanceDate = acceptanceDate;
        this.completionDate = completionDate;
        this.serviceDescription = serviceDescription;
        this.problemDescription = problemDescription;
    }

    public Task(ServiceEntity service, Car car, Customer customer, LocalDateTime acceptanceDate, String serviceDescription, String problemDescription) {
        this.service = service;
        this.car = car;
        this.customer = customer;
        this.acceptanceDate = acceptanceDate;
        this.serviceDescription = serviceDescription;
        this.problemDescription = problemDescription;
    }
}