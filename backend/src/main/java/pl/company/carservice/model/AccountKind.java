package pl.company.carservice.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AccountKind {

    public enum PermissionLevel {
        ADMIN, EMPLOYEE, CUSTOMER, SUPPLIER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    @OneToMany(mappedBy = "accountKind")
    private Set<Account> accounts;

    public AccountKind() {

    }

    public AccountKind(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public AccountKind(Long id, PermissionLevel permissionLevel) {
        this.id = id;
        this.permissionLevel = permissionLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

}
