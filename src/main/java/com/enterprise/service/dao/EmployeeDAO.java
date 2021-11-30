package com.enterprise.service.dao;

import com.enterprise.service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

    @Query("from Employee where designation = ?1")
    List<Employee> findByDesignation(String designation);
}
