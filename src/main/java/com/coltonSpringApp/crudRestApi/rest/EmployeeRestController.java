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

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){

        Employee employeeToFind = employeeService.getEmployeeById(employeeId);

        if(employeeToFind == null){
            throw new RuntimeException("Employee id not found for Id: " + employeeId);
        }

        return employeeToFind;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employeeToCreate){

        // set id to 0 if user passes id in JSON - forces a save instead of an update.
        employeeToCreate.setEmployeeId(0);

        return employeeService.save(employeeToCreate);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employeeToUpdate){
        return employeeService.save(employeeToUpdate);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }
}
