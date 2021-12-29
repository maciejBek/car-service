package pl.company.carservice.dto;

import pl.company.carservice.model.Account;
import pl.company.carservice.model.Customer;

public record AccountCustomerDto(
        Account account,
        Customer customer) {
}
