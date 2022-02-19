package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(unique = true)
    private String emailAddress;

    @ManyToOne
    private AccountKind accountKind;

    public Account(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public Account(String username, String password, String emailAddress, AccountKind accountKind) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.accountKind = accountKind;
    }
}