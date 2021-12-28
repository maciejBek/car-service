package pl.company.carservice.dto;

public record AccountRegistrationDto(
        long id,
        String username,
        String password,
        String retypePassword,
        String emailAddress) {
}
