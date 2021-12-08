package com.enterprise.service.service;

import com.enterprise.service.dao.EmployeeDAO;
import com.enterprise.service.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public boolean createEmployee(Employee employee) {
        Employee result = employeeDAO.saveAndFlush(employee);
        return result != null;
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    public Optional<Employee> getEmployeeWithId(int id) {
        return employeeDAO.findById(id);
    }

    public List<Employee> getEmployeesWithDesignation(String designation) {
        return employeeDAO.findByDesignation(designation);
    }

    public boolean updateEmployee(Employee employee) {
        Employee result = employeeDAO.save(employee);
        return result != null;
    }

    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee);
    }

    private boolean checkIfUsernameAlreadyExists(String username) {
        Optional<Employee> result = employeeDAO.checkIfUsernameExists(username);
        if(result.isPresent()) {
            return false;
        }
        return true;
    }

    public Map<String, Object> checkIfUsernameIsValid(String username) {
        boolean isValid = username.matches("[a-zA-Z0-9]+");
        boolean doesNotExists = checkIfUsernameAlreadyExists(username);

        Map<String, Object> map = new HashMap<>();

        if(isValid && doesNotExists) {
            map.put("status", true);
            return map;
        }

        map.put("status", false);
        map.put("field", "username");

        if(!isValid) {
            map.put("errorMessage", "Username can only contain alphanumeric characters.");
        }
        if (!doesNotExists) {
            map.put("errorMessage", "Username is already taken.");
        }
        return map;
    }
}
