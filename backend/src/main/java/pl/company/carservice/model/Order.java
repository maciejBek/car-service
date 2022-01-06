package pl.company.carservice.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "order_table")
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

    public Order() {

    }

    public Order(LocalDateTime assignmentDate, LocalDateTime date, int totalPrice) {
        this.assignmentDate = assignmentDate;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Order(Supplier supplier, Bill bill, Magazine magazine, LocalDateTime assignmentDate, LocalDateTime date, int totalPrice) {
        this.supplier = supplier;
        this.bill = bill;
        this.magazine = magazine;
        this.assignmentDate = assignmentDate;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public LocalDateTime getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDateTime assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}