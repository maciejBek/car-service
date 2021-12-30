package pl.company.carservice.dto;

import java.sql.Date;

public record TaskAdditionDto(
        Date acceptationDate,
        String serviceDescription,
        String problemDescription) {
}
