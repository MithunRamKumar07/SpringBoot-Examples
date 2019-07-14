package com.example.demo.employeeManagement.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.employeeManagement.Model.db.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{
	
}
