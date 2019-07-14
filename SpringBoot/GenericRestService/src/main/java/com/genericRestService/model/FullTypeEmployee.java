package com.genericRestService.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FullTypeEmployee{

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)  
	private long id;
	
	@Column(nullable = false)
	private int insuranceNo;
	
	@Column(nullable = false)
	private Date dateOfJoining;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	Employee employee;
	
	public int getInsuranceNo() {
		return insuranceNo;
	}
	public void setInsuranceNo(int insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

}
