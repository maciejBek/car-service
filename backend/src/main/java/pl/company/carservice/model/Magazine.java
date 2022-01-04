package pl.company.carservice.model;


import javax.persistence.*;

@Entity
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}