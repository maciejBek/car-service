package pl.company.carservice.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;

    private String name;

    private int hoursCompletionTime;

    public Service() {

    }

    public Service(Bill bill, String name, int hoursCompletionTime) {
        this.bill = bill;
        this.name = name;
        this.hoursCompletionTime = hoursCompletionTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursCompletionTime() {
        return hoursCompletionTime;
    }

    public void setHoursCompletionTime(int hoursCompletionTime) {
        this.hoursCompletionTime = hoursCompletionTime;
    }
}
