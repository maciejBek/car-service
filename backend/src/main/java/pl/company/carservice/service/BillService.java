package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.dto.BillAdditionDto;
import pl.company.carservice.model.Bill;
import pl.company.carservice.repository.BillRepository;
import pl.company.carservice.repository.TaskRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class BillService {

    private BillRepository billRepository;
    private TaskRepository taskRepository;

    @Autowired
    public BillService(BillRepository billRepository, TaskRepository taskRepository) {
        this.billRepository = billRepository;
        this.taskRepository = taskRepository;
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

    public ResponseEntity<?> addBill(BillAdditionDto billAdditionDto) {
        Long taskId = billAdditionDto.taskId();
        Double amount = billAdditionDto.amount();

        Bill bill = new Bill();
        bill.setAmount(amount);

        // adding Bill
        Long addedBillId = this.billRepository.save(bill).getId();

        // updating Bill id
        this.taskRepository.updateBillId(taskId, addedBillId);

        return new ResponseEntity<>(Map.of("id", addedBillId), HttpStatus.OK);
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
