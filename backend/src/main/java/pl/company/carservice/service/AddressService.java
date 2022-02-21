package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pl.company.carservice.controller.error.ErrorResponse;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.Address;
import pl.company.carservice.repository.AddressRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        Optional<Address> fetchedAddress = this.addressRepository.findById(id);
        if (fetchedAddress.isPresent()) {
            return new ResponseEntity<>(fetchedAddress.get(), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("address-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addAddress(@RequestBody Address address) {
        Long addedAddressId = this.addressRepository.save(address).getId();

        return new ResponseEntity<>(Map.of("id", addedAddressId), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        if (this.addressRepository.existsById(id)) {
            this.addressRepository.deleteById(id);
            return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("address-does-not-exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
