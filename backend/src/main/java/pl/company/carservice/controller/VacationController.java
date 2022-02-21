package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.VacationDto;
import pl.company.carservice.service.VacationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class VacationController {

    private VacationService vacationService;

     public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @PostMapping("/vacations")
    public ResponseEntity<?> addVacation(@RequestBody VacationDto vacationDto) {
        return vacationService.addVacation(vacationDto);
    }
}
