package com.genericRestService.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {

	@Id  
	@Column(name = "department_id", nullable = false)  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;

	@Column(nullable = false)
	String departmentName;

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Employee> employees;


	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
