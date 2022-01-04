package pl.company.carservice.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class RegularCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    private int performedServicesNumber;

    public RegularCustomer() {

    }

    public RegularCustomer(Customer customer, int performedServicesNumber) {
        this.customer = customer;
        this.performedServicesNumber = performedServicesNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPerformedServicesNumber() {
        return performedServicesNumber;
    }

    public void setPerformedServicesNumber(int performedServicesNumber) {
        this.performedServicesNumber = performedServicesNumber;
    }
}
