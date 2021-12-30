package pl.company.carservice.dto;

public record EmployeeRegistrationDto(
        String name,
        String surname,
        String phoneNumber,
        String pesel,
        String idNumber) {
}
