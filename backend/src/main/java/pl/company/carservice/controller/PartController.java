package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.model.Part;
import pl.company.carservice.service.CarService;
import pl.company.carservice.service.PartService;

@RequestMapping("/api")
public class PartController {

    private PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping(value = "/parts/{id}", produces = "application/json")
    public ResponseEntity<?> getPart(@PathVariable Long id) {
        return this.partService.getPart(id);
    }

    @PostMapping("/parts")
    public ResponseEntity<?> addPart(@RequestBody Part part) {
        return this.partService.addPart(part);
    }

    @DeleteMapping("/parts/{id}")
    public ResponseEntity<?> deletePart(@PathVariable Long id) {
        return this.partService.deletePart(id);
    }
}
