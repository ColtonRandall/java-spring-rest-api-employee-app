package com.coltonSpringApp.crudRestApi.rest;

import com.coltonSpringApp.crudRestApi.entity.Employee;
import com.coltonSpringApp.crudRestApi.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper){
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
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
    public String deleteEmployee(@PathVariable int employeeId){

        Employee employeeToDelete = employeeService.getEmployeeById(employeeId);

        if (employeeToDelete == null){
            throw new RuntimeException("Employee ID not found - " + employeeId);
        }

        employeeService.deleteEmployeeById(employeeId);

        return "Deleted employee ID - " + employeeId;
    }

    // add mapping for PATCH /employees/{employeeId} - patch employee - partial update
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload){

        Employee tempEmployee = employeeService.getEmployeeById(employeeId);

        // throw exception if not found
        if(tempEmployee == null) {
            throw new RuntimeException("Employee id not found for Id: " + employeeId);
        }

        // throw exception if request body contains "id" key - we don't want users to change primary keys
        if (patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body");
        }

        Employee patchedEmployee = applyPatch(patchPayload, tempEmployee);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    private Employee applyPatch(Map<String, Object> patchPayload, Employee tempEmployee) {

        // convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // Convert patch payload map to json object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);

    }
}
