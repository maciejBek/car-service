package pl.company.carservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String brand;
        private String model;
        private Integer year;
        private String vinNumber;
        private String registrationNumber;
        private Integer capacity;
        private String fuelType;
        private Integer power;
        private Integer tourqe;

        public Car() {

        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getBrand() {
                return brand;
        }

        public void setBrand(String brand) {
                this.brand = brand;
        }

        public String getModel() {
                return model;
        }

        public void setModel(String model) {
                this.model = model;
        }

        public Integer getYear() {
                return year;
        }

        public void setYear(Integer year) {
                this.year = year;
        }

        public String getVinNumber() {
                return vinNumber;
        }

        public void setVinNumber(String vinNumber) {
                this.vinNumber = vinNumber;
        }

        public String getRegistrationNumber() {
                return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
                this.registrationNumber = registrationNumber;
        }

        public Integer getCapacity() {
                return capacity;
        }

        public void setCapacity(Integer capacity) {
                this.capacity = capacity;
        }

        public String getFuelType() {
                return fuelType;
        }

        public void setFuelType(String fuelType) {
                this.fuelType = fuelType;
        }

        public Integer getPower() {
                return power;
        }

        public void setPower(Integer power) {
                this.power = power;
        }

        public Integer getTourqe() {
                return tourqe;
        }

        public void setTourqe(Integer tourqe) {
                this.tourqe = tourqe;
        }
}
