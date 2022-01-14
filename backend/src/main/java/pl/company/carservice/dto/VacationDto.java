package pl.company.carservice.dto;

public record VacationDto (
    String startDate,
    String endDate,
    Long accountId){
}
