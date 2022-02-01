package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class RegularCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    private int performedServicesNumber;

    public RegularCustomer(Customer customer, int performedServicesNumber) {
        this.customer = customer;
        this.performedServicesNumber = performedServicesNumber;
    }
}