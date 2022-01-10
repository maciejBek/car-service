package pl.company.carservice.dto;

import java.time.LocalDateTime;

public record CarCustomerServiceTaskDto(
        String brand,
        String model,
        String customerName,
        String customerSurname,
        String serviceName,
        Long taskId,
        LocalDateTime acceptanceDate,
        LocalDateTime completionDate,
        String serviceDescription,
        String problemDescription
) {
}


//- z Car: marka samochodu, model samochodu;
//- z Customer: imie, nazwisko
//- z Service: nazwa uslugi
//- z Task: id tasku, opis uslugi, opis problemu, data przyjecia, data ukonczenia