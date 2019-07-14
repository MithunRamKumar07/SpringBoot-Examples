package com.genericRestService.services;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.genericRestService.model.Employee;

@Service
@Primary
public class EmployeeService extends GenericService<Employee>  {
	
}
