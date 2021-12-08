package com.enterprise.service.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username can not be blank or null.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    @Column(length = 20)
    private String username;

    @NotBlank(message = "Name can not be blank or null")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters.")
    @Column(length = 20)
    private String name;

    @NotBlank(message = "Designation can not be blank/null/empty.")
    @Size(min = 3, max = 20, message = "Designation must be between 3 and 64 characters.")
    @Column(length = 64)
    private String designation;

    @NotNull(message = "Salary can not be blank or null.")
    @Min(100)
    private double salary;

    public Employee() { }

    public Employee(String username, String name, String designation, double salary) {
        this.username = username;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                '}';
    }
}
