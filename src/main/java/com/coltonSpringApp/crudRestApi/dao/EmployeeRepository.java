package com.coltonSpringApp.crudRestApi.dao;

import com.coltonSpringApp.crudRestApi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /*
        Replace the DAOs with the JPA Repository.
        No other code required due to built-in methods by extending JpaRepository.
            - i.e. findAll(), deleteById(), save(), getReferenceById()
        Jpa Repository also provides 'Transactional' out of the box, so no need for this.
     */
}
