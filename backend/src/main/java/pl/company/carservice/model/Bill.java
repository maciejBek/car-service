package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Bill {

    public enum PaymentMethod {
        CASH, CARD, BLIK;
    }

    public enum TransactionType {
        PROFIT, LOSS;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ServiceEntity service;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Boolean isPaid;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public Bill(Double amount) {
        this.amount = amount;
    }
}