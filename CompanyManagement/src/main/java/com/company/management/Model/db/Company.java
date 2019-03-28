package com.company.management.Model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Company")
public class Company {

	@Id
	@Column(name = "company_name")
	String companyName;
	
	@Column(name = "city")
	String city;
	
	@Column(name = "country")
	String country;
	
	@Column(name = "fortune_500")
	Boolean fortune500;
	
	@Column(name = "founded")
	Date founded;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getFortune500() {
		return fortune500;
	}

	public void setFortune500(Boolean fortune500) {
		this.fortune500 = fortune500;
	}

	public Date getFounded() {
		return founded;
	}

	public void setFounded(Date founded) {
		this.founded = founded;
	}


	
}
