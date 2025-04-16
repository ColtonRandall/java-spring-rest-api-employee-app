package com.coltonSpringApp.crudRestApi.rest;

import com.coltonSpringApp.crudRestApi.dao.EmployeeDAO;
import com.coltonSpringApp.crudRestApi.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;

    // quick initial solution: inject employee DAO directly using constructor injection
    public EmployeeRestController(EmployeeDAO theEmployeeDao){
        employeeDAO = theEmployeeDao;
    }

    // expose "/employees" and return a List of Employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }
}
