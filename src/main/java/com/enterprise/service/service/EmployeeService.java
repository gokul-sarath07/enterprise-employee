package com.enterprise.service.service;

import com.enterprise.service.dao.EmployeeDAO;
import com.enterprise.service.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
