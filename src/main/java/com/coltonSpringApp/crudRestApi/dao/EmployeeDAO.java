package com.coltonSpringApp.crudRestApi.dao;

import com.coltonSpringApp.crudRestApi.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAllEmployees();
}
