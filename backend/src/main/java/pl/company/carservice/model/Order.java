package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "order_table")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name="order_part",
            joinColumns=@JoinColumn(name="id"),
            inverseJoinColumns=@JoinColumn(name="order_id"))
    private Set<Part> parts;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Supplier supplier;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Magazine magazine;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime assignmentDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private int totalPrice;

    public Order(LocalDateTime assignmentDate, LocalDateTime date, int totalPrice) {
        this.assignmentDate = assignmentDate;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Order(Supplier supplier, Magazine magazine, LocalDateTime assignmentDate, LocalDateTime date, int totalPrice) {
        this.supplier = supplier;
        this.magazine = magazine;
        this.assignmentDate = assignmentDate;
        this.date = date;
        this.totalPrice = totalPrice;
    }
}