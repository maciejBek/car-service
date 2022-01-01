package pl.company.carservice.dto;

public record CarCustomerIdDto(
        String brand,
        String model,
        Integer year,
        String vinNumber,
        String registrationNumber,
        Integer capacity,
        String fuelType,
        Integer power,
        Integer tourqe,
        Long customerId) {
}
