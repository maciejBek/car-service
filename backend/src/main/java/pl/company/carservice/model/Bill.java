package pl.company.carservice.model;

import javax.persistence.*;

@Entity
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

    @Enumerated(EnumType.STRING)
    private String paymentMethod;
    private Boolean isPaid;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private String transactionType;

    public Bill() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}