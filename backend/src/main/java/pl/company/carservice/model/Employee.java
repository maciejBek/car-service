package pl.company.carservice.model;

public record Employee(
        int id,
        int positionId,
        String name,
        String surname,
        String adress,
        String phoneNumber,
        String peselNumber,
        String idCardNumber
        ) {
}
