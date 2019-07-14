package com.genericRestService.model;

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
public class VendorEmployee{

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)  
	private long id;
	
	@Column(nullable = false)
	String vendorName;
	
	@Column(nullable = false)
	int vendorPeriod;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	Employee employee;
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public int getVendorPeriod() {
		return vendorPeriod;
	}
	public void setVendorPeriod(int vendorPeriod) {
		this.vendorPeriod = vendorPeriod;
	}

	
}
