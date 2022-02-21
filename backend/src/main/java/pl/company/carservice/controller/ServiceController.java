package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.model.ServiceEntity;
import pl.company.carservice.service.CarService;
import pl.company.carservice.service.ServiceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping(value = "/services/{id}", produces = "application/json")
    public ResponseEntity<?> getService(@PathVariable Long id) {
        return this.serviceService.getService(id);
    }

    @GetMapping(value = "/services", produces = "application/json")
    public ResponseEntity<?> getServices() {
        return this.serviceService.getServices();
    }

    @PostMapping("/services")
    public ResponseEntity<?> addService(@RequestBody ServiceEntity service) {
        return this.serviceService.addService(service);
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        return this.serviceService.deleteService(id);
    }
}