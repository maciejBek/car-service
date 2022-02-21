package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.company.carservice.StringToJson;
import pl.company.carservice.model.Part;
import pl.company.carservice.repository.PartRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class PartService {

    private PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public ResponseEntity<?> getPart(Long id) {
        Optional<Part> fetchedPart = this.partRepository.findById(id);

        if (fetchedPart.isPresent()) {
            Part part = fetchedPart.get();
            return new ResponseEntity<>(part, HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "part-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addPart(Part part) {
        Long addedPartId = this.partRepository.save(part).getId();

        return new ResponseEntity<>(Map.of("id", addedPartId), HttpStatus.OK);
    }

    public ResponseEntity<?> deletePart(Long id) {
        if (this.partRepository.existsById(id)) {
            this.partRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            String errorResponse = StringToJson.parse("error", "part-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}