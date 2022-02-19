package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class AccountKind {

    public enum PermissionLevel {
        ADMIN, EMPLOYEE, CUSTOMER, SUPPLIER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    public AccountKind(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public AccountKind(Long id, PermissionLevel permissionLevel) {
        this.id = id;
        this.permissionLevel = permissionLevel;
    }
}