package com.coltonSpringApp.crudRestApi.dao;

import com.coltonSpringApp.crudRestApi.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();
    // Get one Employee by Id
    Employee getEmployeeById(int employeeId);
    // Update Employee
    Employee save(Employee theEmployee);
    // Delete Employee
    void deleteEmployeeById(int employeeId);
}
