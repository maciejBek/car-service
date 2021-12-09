package pl.company.carservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String brand;
        private String model;
        private int year;
        private String vinNumber;
        private String registrationNumber;
        private int capacity;
        private String fuelType;
        private int power;
        private int tourqe;

        public long getId() {
                return id;
        }

        public void setId(long id) {
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

        public int getYear() {
                return year;
        }

        public void setYear(int year) {
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

        public int getCapacity() {
                return capacity;
        }

        public void setCapacity(int capacity) {
                this.capacity = capacity;
        }

        public String getFuelType() {
                return fuelType;
        }

        public void setFuelType(String fuelType) {
                this.fuelType = fuelType;
        }

        public int getPower() {
                return power;
        }

        public void setPower(int power) {
                this.power = power;
        }

        public int getTourqe() {
                return tourqe;
        }

        public void setTourqe(int tourqe) {
                this.tourqe = tourqe;
        }
}
