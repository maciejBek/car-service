package pl.company.carservice.dto;

public record AccountRegistrationDto(
        String username,
        String password,
        String retypePassword,
        String emailAddress
) {
}
