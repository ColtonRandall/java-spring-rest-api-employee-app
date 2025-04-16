package com.coltonSpringApp.crudRestApi.dao;

import com.coltonSpringApp.crudRestApi.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entityManager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        // create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }

    @Override
    public Employee save(Employee theEmployee) {
        /*
            - if id = 0, then insert/save entry into db, else update.
            - return the updated Id from the database
         */
        return entityManager.merge(theEmployee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        // find the employee first
        Employee employeeToDelete = entityManager.find(Employee.class, employeeId);
        entityManager.remove(employeeToDelete);
    }
}
