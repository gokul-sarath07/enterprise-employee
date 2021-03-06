package com.enterprise.service.controller;

import com.enterprise.service.model.Employee;
import com.enterprise.service.model.Error;
import com.enterprise.service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if(errors.hasErrors()) {
            List<Error> errorList = new Error().getErrorDetails(errors.getAllErrors());
            return new ResponseEntity<List<Error>>(errorList, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> map = employeeService.checkIfUsernameIsValid(employee.getUsername());

        if( (boolean) map.get("status")) {
            boolean isSuccess = employeeService.createEmployee(employee);
            if (isSuccess) {
                return new ResponseEntity<String>("Employee has been created.", HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Employee creation failed.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> result = employeeService.getAllEmployees();
        if (result.size() > 0) {
            return new ResponseEntity<List<Employee>>(result, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Employee list is empty.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeWithId(@PathVariable("id") int id) {
        Optional<Employee> result = employeeService.getEmployeeWithId(id);
        if (result.isPresent()) {
            return new ResponseEntity<Employee>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Employee with ID " + id + " doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/designation")
    public ResponseEntity<?> getEmployeesWithDesignation(@RequestParam("designation") String designation) {
        List<Employee> result = employeeService.getEmployeesWithDesignation(designation);
        if (result.size() > 0) {
            return new ResponseEntity<List<Employee>>(result, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Employees with given designation doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if(errors.hasErrors()) {
            List<Error> errorList = new Error().getErrorDetails(errors.getAllErrors());
            return new ResponseEntity<List<Error>>(errorList, HttpStatus.BAD_REQUEST);
        }

        boolean isSuccess = employeeService.updateEmployee(employee);
        if (isSuccess) {
            return new ResponseEntity<String>("Employee details has been updated.", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Employee updating failed.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEmployee(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
        return new ResponseEntity<String>("Employee has been deleted.", HttpStatus.OK);
    }
}
