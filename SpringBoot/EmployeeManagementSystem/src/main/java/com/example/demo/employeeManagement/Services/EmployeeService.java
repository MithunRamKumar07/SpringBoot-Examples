package com.example.demo.employeeManagement.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.employeeManagement.Model.db.Employee;
import com.example.demo.employeeManagement.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;
	
	public Employee getEmployee(int empId) {
		return employeeRepo.findById(empId).get();
	}
	
	public Iterable<Employee> getEmployees() {
		return employeeRepo.findAll();
	}
	
	public void addEmployee(Employee employee) {
		employeeRepo.save(employee);
		
	}
	
	public void updateEmployee(Employee employee) {
		employeeRepo.save(employee);
	}
	
	public void deleteEmployee(int empId) {
		employeeRepo.deleteById(empId);
	}
	
}
