package com.genericRestService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genericRestService.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends GenericController<Employee>{

}
