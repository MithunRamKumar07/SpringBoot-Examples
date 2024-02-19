package com.mithun.springboot.transactional.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mithun.springboot.transactional.model.Employee;
import com.mithun.springboot.transactional.model.EmployeeDetailsDto;
import com.mithun.springboot.transactional.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/employees")
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/save")
    public String saveEmployees(@RequestBody EmployeeDetailsDto employeeDetailsDto){
         employeeService.saveEmployees(employeeDetailsDto);
         return "Successfully inserted the employees";
    }

    @GetMapping("/getAll")
    public EmployeeDetailsDto getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/sort/{sortingField}/getAll")
    public EmployeeDetailsDto sortAndGetAllEmployees(@PathVariable String sortingField){
        return employeeService.getAllEmployeesAfterSorting(sortingField);
    }

    @GetMapping("/sortAndPagination/{sortingField}/{pageNumber}/{pageSize}/getAll")
    public ResponseEntity<Page<Employee>> sortAndPaginateAndGetAllEmployees(@PathVariable String sortingField, @PathVariable int pageNumber
            , @PathVariable int pageSize){
        Page<Employee> response = employeeService.getAllEmployeesAfterSortingAndPagination(sortingField,pageNumber,pageSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{employeeId}/get")
    public ResponseEntity<Employee> getEmployeeByJpaQuery(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeService.getEmployeeByIdUsingJpaQuery(employeeId));
    }
}
