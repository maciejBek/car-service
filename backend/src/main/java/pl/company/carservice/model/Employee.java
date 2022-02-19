package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EmployeePosition employeePosition;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;

    @OneToOne
    private Account account;

    private String name;
    private String surname;
    private String phoneNumber;
    private String pesel;
    private String idNumber;

    public Employee(String name, String surname, String phoneNumber, String pesel, String idNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
        this.idNumber = idNumber;
    }

    public Employee(EmployeePosition employeePosition, Address address, Account account, String name, String surname, String phoneNumber, String pesel, String idNumber) {
        this.employeePosition = employeePosition;
        this.address = address;
        this.account = account;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
        this.idNumber = idNumber;
    }
}