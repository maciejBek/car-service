package pl.company.carservice.dto;

public record NeededTaskPartsDto(
        String partName,
        String partNumbering,
        String partSerialNumber,
        String carBrand,
        String carModel,
        Integer partsAmount,
        Integer taskId) {
}
