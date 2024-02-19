package com.mithun.springboot.transactional.dao;

import com.mithun.springboot.transactional.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?",
            nativeQuery = true)
    Employee findEmployeeById(int employeeId);
}
