package com.user.loginService.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User{
	
	@Column(unique = true)
	String username;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)  
	long id;
	
	@Column(nullable = false)  
	String emailAddress;
	
	@Column(nullable = false)  
	String password;
	
	@Column(nullable = false)  
	Date lastLoginDate;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	
	

	@Override
	public String toString() {
		return "Employee [name=" + username + ", id=" + id + ", emailAddress=" + emailAddress + ", password=" + password
				+ ", lastLoginDate=" + lastLoginDate + "]";
	}

	
	
}
