package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int estimatedHoursCompletionTime;
    private int priceFrom;
    private int priveTo;

    public ServiceEntity(String name, int estimatedHoursCompletionTime, int priceFrom, int priveTo) {
        this.name = name;
        this.estimatedHoursCompletionTime = estimatedHoursCompletionTime;
        this.priceFrom = priceFrom;
        this.priveTo = priveTo;
    }
}