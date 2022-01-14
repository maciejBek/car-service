package pl.company.carservice.model;

import javax.persistence.*;
import java.util.Set;

@Entity
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

    public Part() {

    }

    public Part(String name, String numbering, String serialNumber, String carBrand, String carModel) {
        this.name = name;
        this.numbering = numbering;
        this.serialNumber = serialNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbering() {
        return numbering;
    }

    public void setNumbering(String numbering) {
        this.numbering = numbering;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}