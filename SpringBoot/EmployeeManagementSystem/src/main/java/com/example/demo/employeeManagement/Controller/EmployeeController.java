package com.example.demo.employeeManagement.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.employeeManagement.Model.db.Employee;
import com.example.demo.employeeManagement.Services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/get/{empId}")
	public Employee getEmployee(@PathVariable int empId) {

		Employee emp = employeeService.getEmployee(empId);
		return emp;
	}

	@GetMapping("/get")
	public List<Employee> getEmployees() {

		List<Employee> employeeList = new ArrayList<Employee>();
		Iterable<Employee> employees = employeeService.getEmployees();
		employees.forEach(employee -> employeeList.add(employee));
		return employeeList;
	}
	
	
	
	@GetMapping("/restTemplate")
	public List<Object> restTemplate() {

		//Communicating between services using Eureka and Rest template
		ResponseEntity<List<Object>> companyListResponse = restTemplate.exchange("http://Company-Management-System/company/get" , HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Object>>() {
                });
		
		List<Object> companyList = companyListResponse.getBody();
		companyList.forEach(company->System.out.println("The object is printed successfully"));
		return companyList;
	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {

		employeeService.addEmployee(employee);
		return employee;
	}

	@PutMapping("/update/{empId}")
	public Employee updateEmployee(@RequestBody Employee employee) {

		employeeService.updateEmployee(employee);
		return employee;
	}

	@DeleteMapping("/delete/{empId}")
	public void deleteEmployee(@PathVariable int empId) {

		employeeService.deleteEmployee(empId);
	}

}
