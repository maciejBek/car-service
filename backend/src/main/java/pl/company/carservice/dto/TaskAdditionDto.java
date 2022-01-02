package pl.company.carservice.dto;

import java.sql.Date;

public record TaskAdditionDto(
        Long serviceId,
        Long carId,
        Long customerId,
        Date acceptationDate,
        String serviceDescription,
        String problemDescription) {
}
