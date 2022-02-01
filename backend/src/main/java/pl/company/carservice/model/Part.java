package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "parts")
    private Set<Order> orders;

    private String name;
    private String numbering;
    private String serialNumber;
    private String carBrand;
    private String carModel;
    private String carModelSymbol;
    private int carYear;

    public Part(String name, String numbering, String serialNumber, String carBrand, String carModel) {
        this.name = name;
        this.numbering = numbering;
        this.serialNumber = serialNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }
}