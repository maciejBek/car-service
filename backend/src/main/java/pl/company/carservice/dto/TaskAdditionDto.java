package pl.company.carservice.dto;

public record TaskAdditionDto(
        Long serviceId,
        Long carId,
        Long customerId,
        String acceptanceDate,
        String serviceDescription,
        String problemDescription) {
}
