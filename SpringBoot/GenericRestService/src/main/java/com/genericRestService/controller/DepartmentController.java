package com.genericRestService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genericRestService.model.Department;

@RestController
@RequestMapping("/department")
public class DepartmentController extends GenericController<Department>{
	
}
