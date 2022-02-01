package pl.company.carservice.dto;

public record TaskAdditionByAccountIdDto(
        Long serviceId,
        Long carId,
        Long accountId,
        String acceptanceDate,
        String serviceDescription,
        String problemDescription
) {
}
