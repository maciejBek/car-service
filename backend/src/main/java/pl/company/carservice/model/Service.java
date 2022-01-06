package pl.company.carservice.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;

    @ManyToMany(mappedBy="services")
    private Set<Car> cars;

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

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
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
