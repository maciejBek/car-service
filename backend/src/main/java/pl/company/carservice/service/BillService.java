package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.company.carservice.StringToJson;
import pl.company.carservice.dto.BillAdditionDto;
import pl.company.carservice.dto.TaskIdDto;
import pl.company.carservice.model.Bill;
import pl.company.carservice.model.Task;
import pl.company.carservice.repository.BillRepository;
import pl.company.carservice.repository.TaskRepository;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;

@Service
public class BillService {

    private BillRepository billRepository;
    private TaskRepository taskRepository;
    private EntityManager entityManager;

    @Autowired
    public BillService(BillRepository billRepository, TaskRepository taskRepository, EntityManager entityManager) {
        this.billRepository = billRepository;
        this.taskRepository = taskRepository;
        this.entityManager = entityManager;
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

    public ResponseEntity<?> getBillPriceByTaskId(TaskIdDto taskIdDto) {
        Long taskId = taskIdDto.taskId();
        System.out.println("taskid:" + taskId);
        Long billId = this.entityManager.getReference(Task.class, taskId).getBill().getId();
        System.out.println("billId: " + billId);
        Double amount = this.entityManager.getReference(Bill.class, billId).getAmount();

        return new ResponseEntity<>(Map.of("amount", amount), HttpStatus.OK);
    }
}
