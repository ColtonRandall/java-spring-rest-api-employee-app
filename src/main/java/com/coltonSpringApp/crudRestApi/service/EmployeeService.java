package com.coltonSpringApp.crudRestApi.service;

import com.coltonSpringApp.crudRestApi.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();
}
