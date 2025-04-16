package com.coltonSpringApp.crudRestApi.service;

import com.coltonSpringApp.crudRestApi.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int employeeId);
    // Update Employee
    Employee save(Employee theEmployee);
    // Delete Employee
    void deleteEmployeeById(int employeeId);
}
