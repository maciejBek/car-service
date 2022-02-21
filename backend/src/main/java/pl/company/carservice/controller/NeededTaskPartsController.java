package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.dto.NeededTaskPartsDto;
import pl.company.carservice.model.NeededTaskParts;
import pl.company.carservice.service.CarService;
import pl.company.carservice.service.NeededTaskPartsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class NeededTaskPartsController {

    private NeededTaskPartsService neededTaskPartsService;

    public NeededTaskPartsController(NeededTaskPartsService neededTaskPartsService) {
        this.neededTaskPartsService = neededTaskPartsService;
    }

    @PostMapping("/needed-task-parts")
    public ResponseEntity<?> addNeededTaskParts(@RequestBody NeededTaskPartsDto neededTaskPartsDto) {
        return this.neededTaskPartsService.addNeededTaskParts(neededTaskPartsDto);
    }

}
