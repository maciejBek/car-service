package pl.company.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.company.carservice.StringToJson;
import pl.company.carservice.dto.VacationDto;
import pl.company.carservice.model.Account;
import pl.company.carservice.model.Car;
import pl.company.carservice.model.Employee;
import pl.company.carservice.model.Vacation;
import pl.company.carservice.repository.AccountRepository;
import pl.company.carservice.repository.EmployeeRepository;
import pl.company.carservice.repository.VacationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class VacationService {

    private VacationRepository vacationRepository;
    private EmployeeRepository employeeRepository;

    public VacationService(VacationRepository vacationRepository, EmployeeRepository employeeRepository) {
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<?> addVacation(VacationDto vacationDto) {
        LocalDate startDate = LocalDate.parse(vacationDto.startDate());
        LocalDate endDate = LocalDate.parse(vacationDto.endDate());
        Long accountId = vacationDto.accountId();

        Optional<Employee> fetchedEmployee = this.employeeRepository.findByAccount_Id(accountId);

        if (!fetchedEmployee.isPresent()) {
            String errorResponse = StringToJson.parse("error", "employee-does-not-exist");
            System.out.println("error");

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        Vacation vacation = new Vacation(fetchedEmployee.get(), startDate, endDate);

        // save to database
        Long vacationId = vacationRepository.save(vacation).getId();

        return new ResponseEntity<>(Map.of("id", vacationId), HttpStatus.OK);
    }

}
