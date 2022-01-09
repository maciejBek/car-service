package pl.company.carservice.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    public Task() {

    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(LocalDateTime acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }
}