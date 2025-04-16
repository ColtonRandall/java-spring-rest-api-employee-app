package com.coltonSpringApp.crudRestApi.rest;

import com.coltonSpringApp.crudRestApi.entity.Employee;
import com.coltonSpringApp.crudRestApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    // expose "/employees" and return a List of Employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // Get single employee based on index
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){

        Employee employeeToFind = employeeService.getEmployeeById(employeeId);

        if(employeeToFind == null){
            throw new RuntimeException("Employee id not found for Id: " + employeeId);
        }

        return employeeToFind;
    }

    // Create a new employee
    @PostMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employeeToUpdate){

        // set id to 0 if user passes id in JSON - forces a save instead of an update.
        employeeToUpdate.setEmployeeId(0);

        return employeeService.save(employeeToUpdate);
    }
}
