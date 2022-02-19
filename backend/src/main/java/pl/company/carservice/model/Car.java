package pl.company.carservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(updatable = false)
        private Long id;

        @OnDelete(action = OnDeleteAction.CASCADE)
        @ManyToOne
        private Customer customer;

        private String brand;
        private String model;
        private Integer year;

        @Column(unique = true)
        private String vinNumber;
        private String registrationNumber;
        private Integer capacity;
        private String fuelType;
        private Integer power;
        private Integer tourqe;

        public Car(String brand, String model, Integer year, String vinNumber, String registrationNumber, Integer capacity, String fuelType, Integer power, Integer tourqe) {
                this.brand = brand;
                this.model = model;
                this.year = year;
                this.vinNumber = vinNumber;
                this.registrationNumber = registrationNumber;
                this.capacity = capacity;
                this.fuelType = fuelType;
                this.power = power;
                this.tourqe = tourqe;
        }
}