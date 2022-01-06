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
    private int quantity;

    public Part() {

    }

    public Part(String name, String numbering, String serialNumber, String carBrand, String carModel, String carModelSymbol, int carYear, int quantity) {
        this.name = name;
        this.numbering = numbering;
        this.serialNumber = serialNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carModelSymbol = carModelSymbol;
        this.carYear = carYear;
        this.quantity = quantity;
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

    public String getCarModelSymbol() {
        return carModelSymbol;
    }

    public void setCarModelSymbol(String carModelSymbol) {
        this.carModelSymbol = carModelSymbol;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}