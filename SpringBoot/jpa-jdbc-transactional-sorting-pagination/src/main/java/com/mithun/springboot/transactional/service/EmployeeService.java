package com.mithun.springboot.transactional.service;

import com.mithun.springboot.transactional.dao.EmployeeCompensationRepository;
import com.mithun.springboot.transactional.dao.EmployeeRepository;
import com.mithun.springboot.transactional.dao.EmployeeRowMapper;
import com.mithun.springboot.transactional.model.Employee;
import com.mithun.springboot.transactional.model.EmployeeDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {

    private static final String insertQueryEmployee = "INSERT INTO EMPLOYEE (NAME,EMPLOYEE_ID,AGE) VALUES (?,?,?);";
    private static final String insertQueryEmployeeCompensation = "INSERT INTO EMPLOYEE_COMPENSATION(COMPENSATION_ID,COST_TO_THE_COMPANY,EMPLOYEE_ID,SALARY) VALUES (?,?,?,?);";

    @Autowired
    EmployeeCompensationRepository employeeCompensationRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    JdbcTemplate jdbcTemplate;

    Environment environment;

    public EmployeeService(JdbcTemplate jdbcTemplate, Environment environment){
        this.jdbcTemplate = jdbcTemplate;
        this.environment = environment;
    }

    @Transactional
    public EmployeeDetailsDto saveEmployees(EmployeeDetailsDto employeeDetailsDto) {
        if(Arrays.asList(this.environment.getActiveProfiles()).contains("Jpa")){
            callJpaApiToSaveEmployees(employeeDetailsDto);
        }else{
            callJdbcApiToSaveEmployees(employeeDetailsDto);
        }
        return employeeDetailsDto;
    }

    @Transactional
    public EmployeeDetailsDto getAllEmployees(){
        if(Arrays.asList(this.environment.getActiveProfiles()).contains("Jpa")){
            List<Employee> employees = employeeRepository.findAll();
            return new EmployeeDetailsDto(employees);
        }else{
            List<Employee> employees = jdbcTemplate.query("select a.*, b.* from EMPLOYEE a, EMPLOYEE_COMPENSATION B where a.EMPLOYEE_ID=b.EMPLOYEE_ID;",
                    new EmployeeRowMapper());
            return new EmployeeDetailsDto(employees);
        }
    }

    private List<Employee> callJpaApiToSaveEmployees(EmployeeDetailsDto employeeDetailsDto) {
            //Insert into Employee & EmployeeCompensation Table
            return employeeRepository.saveAll(employeeDetailsDto.getEmployees());
    }

    void callJdbcApiToSaveEmployees(EmployeeDetailsDto employeeDetailsDto) {
        //Insert into Employee Table
        employeeDetailsDto.getEmployees().stream().forEach(employee -> {
            int result = jdbcTemplate.update(insertQueryEmployee,employee.getName(),employee.getEmployeeId(),employee.getAge());
            if (result==1){
                //Insert into Employee_Compensation Table
                    jdbcTemplate.update(insertQueryEmployeeCompensation,employee.getEmployeeCompensation().getEmployeeId(),
                        employee.getEmployeeCompensation().getCostToTheCompany(),employee.getEmployeeCompensation().getEmployeeId()
                        ,employee.getEmployeeCompensation().getSalary());
            }
        });
    }

    public EmployeeDetailsDto getAllEmployeesAfterSorting(String sortingField) {
        return new EmployeeDetailsDto(employeeRepository.findAll(Sort.by(Sort.Direction.ASC,sortingField)));
    }

    public Page<Employee> getAllEmployeesAfterSortingAndPagination(String sortingField,int pageNumber,int pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageNumber,pageSize)
                .withSort(Sort.by(Sort.Direction.DESC,sortingField)));
    }

    @Query(value = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID in (1,2,3,4)",
            nativeQuery = true)
    public Employee getEmployeeByIdUsingJpaQuery(int employeeId) {
        return employeeRepository.findEmployeeById(employeeId);
    }
}
