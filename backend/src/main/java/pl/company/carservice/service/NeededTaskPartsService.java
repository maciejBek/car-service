package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.dto.CarCustomerIdDto;
import pl.company.carservice.dto.NeededTaskPartsDto;
import pl.company.carservice.model.NeededTaskParts;
import pl.company.carservice.model.Part;
import pl.company.carservice.model.Task;
import pl.company.carservice.repository.NeededTaskPartsRepository;
import pl.company.carservice.repository.PartRepository;

import javax.persistence.EntityManager;
import java.util.Map;

@Service
public class NeededTaskPartsService {

    private NeededTaskPartsRepository neededTaskPartsRepository;
    private PartRepository partRepository;
    private EntityManager entityManager;

    @Autowired
    public NeededTaskPartsService(NeededTaskPartsRepository neededTaskPartsRepository, PartRepository partRepository, EntityManager entityManager) {
        this.neededTaskPartsRepository = neededTaskPartsRepository;
        this.partRepository = partRepository;
        this.entityManager = entityManager;
    }

//    public ResponseEntity<?> getCars(
//            @RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "100") Integer pageSize,
//            @RequestParam(defaultValue = "brand") String sortBy,
//            @RequestParam(defaultValue = "") Long customerId) {
//
//        return this.neededTaskPartsService.getNeededTaskParts(pageNo, pageSize, sortBy, customerId);
//    }

    public ResponseEntity<?> addNeededTaskParts(@RequestBody NeededTaskPartsDto neededTaskPartsDto) {
        String partName = neededTaskPartsDto.partName();
        String partNumbering = neededTaskPartsDto.partNumbering();
        String partSerialNumber = neededTaskPartsDto.partSerialNumber();
        String carBrand = neededTaskPartsDto.carBrand();
        String carModel = neededTaskPartsDto.carModel();
        Long taskId = neededTaskPartsDto.taskId().longValue();
        Integer partsAmount = neededTaskPartsDto.partsAmount();

        // checking if part exists
        boolean exists = this.neededTaskPartsRepository.existsByPartNumberingAndPartSerialNumber(partNumbering, partSerialNumber);
        if (exists) {
            ErrorResponse errorResponse = new ErrorResponse("part-exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        // adding part
        Part part = new Part(partName, partNumbering, partSerialNumber, carBrand, carModel);
        part = this.partRepository.save(part);

        System.out.println("taskId: " + taskId);
        Task task = entityManager.getReference(Task.class, taskId);
        NeededTaskParts neededTaskParts = new NeededTaskParts(part, task, partsAmount);

        // add neededTaskParts
        Long neededTaskPartsId = this.neededTaskPartsRepository.save(neededTaskParts).getId();

        return new ResponseEntity<>(Map.of("id", neededTaskPartsId), HttpStatus.OK);
    }
}
