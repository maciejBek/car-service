package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.model.Bill;
import pl.company.carservice.repository.BillRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class BillService {

    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;;
    }

    public ResponseEntity<?> getBill(Long id) {
        Optional<Bill> fetchedBill = this.billRepository.findById(id);

        if (fetchedBill.isPresent()) {
            Bill bill = fetchedBill.get();
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "bill-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addBill(Bill bill) {
        Long addedPartId = this.billRepository.save(bill).getId();

        return new ResponseEntity<>(Map.of("id", addedPartId), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteBill(Long id) {
        if (this.billRepository.existsById(id)) {
            this.billRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "bill-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
