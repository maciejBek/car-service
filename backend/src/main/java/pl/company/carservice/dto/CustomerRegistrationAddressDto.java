package pl.company.carservice.dto;

import pl.company.carservice.model.Address;

public record CustomerRegistrationAddressDto(
        String name,
        String surname,
        String phoneNumber,
        Address address) {
}