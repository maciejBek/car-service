package pl.company.carservice.model;

import javax.persistence.*;

@Entity
public class EmployeePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String kind;

}
