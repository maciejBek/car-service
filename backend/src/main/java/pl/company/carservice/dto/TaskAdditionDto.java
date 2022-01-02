package pl.company.carservice.dto;

import java.sql.Date;

public record TaskAdditionDto(
        Long serviceId,
        Long carId,
        Long customerId,
        Date acceptationDate,
        Date completionDate,
        String serviceDescription,
        String problemDescription) {
}
