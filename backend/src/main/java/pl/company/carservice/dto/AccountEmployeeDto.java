package pl.company.carservice.dto;

import pl.company.carservice.model.Account;
import pl.company.carservice.model.Employee;

public record AccountEmployeeDto(
        Account account,
        Employee employee) implements AccountUserDtoInterface {
}
