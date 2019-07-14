package com.genericRestService.dao;

import org.springframework.stereotype.Repository;

import com.genericRestService.model.Employee;

@Repository
public interface EmployeeDao extends GenericDao<Employee>{
	
}
