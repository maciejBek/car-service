package pl.company.carservice.model;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int estimatedHoursCompletionTime;
    private int priceFrom;
    private int priveTo;

    public ServiceEntity() {

    }

    public ServiceEntity(String name, int estimatedHoursCompletionTime, int priceFrom, int priveTo) {
        this.name = name;
        this.estimatedHoursCompletionTime = estimatedHoursCompletionTime;
        this.priceFrom = priceFrom;
        this.priveTo = priveTo;
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

    public int getHoursCompletionTime() {
        return estimatedHoursCompletionTime;
    }

    public void setHoursCompletionTime(int estimatedHoursCompletionTime) {
        this.estimatedHoursCompletionTime = estimatedHoursCompletionTime;
    }

    public int getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(int priceFrom) {
        this.priceFrom = priceFrom;
    }

    public int getPriveTo() {
        return priveTo;
    }

    public void setPriveTo(int priveTo) {
        this.priveTo = priveTo;
    }
}
