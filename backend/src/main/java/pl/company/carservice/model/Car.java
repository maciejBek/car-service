package pl.company.carservice.model;

public record Car(
        int id,
        String brand,
        String model,
        int year,
        String vinNumber,
        String registrationNumber,
        int capacity,
        String fuelType,
        int power,
        int tourqe
        ) {
}
