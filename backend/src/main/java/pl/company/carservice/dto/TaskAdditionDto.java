package pl.company.carservice.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public record TaskAdditionDto(
        Long serviceId,
        Long carId,
        Long customerId,
        LocalDateTime acceptationDate,
        LocalDateTime completionDate,
        String serviceDescription,
        String problemDescription) {
}
