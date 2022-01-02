package pl.company.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.service.BillService;

@RestController
public class BillController {

    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping(value = "/bills/{id}", produces = "application/json")
    public ResponseEntity<?> getBill(@PathVariable Long id) {
        return this.billService.getBill(id);
    }

    @PostMapping("/bills")
    public ResponseEntity<?> addBill(@RequestBody CarCustomerIdDto carCustomerIdDto) {
        return this.billService.addBill(carCustomerIdDto);
    }

    @DeleteMapping("/bills/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable Long id) {
        return this.billService.deleteBill(id);
    }
}
