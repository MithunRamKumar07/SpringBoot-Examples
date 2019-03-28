package com.company.management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.management.Model.db.Company;
import com.company.management.Repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepo;
	
	public Company getCompany(String companyName) {
		return companyRepo.findById(companyName).get();
	}
	
	public Iterable<Company> getCompanies() {
		return companyRepo.findAll();
	}
	
	public void addCompany(Company company) {
		companyRepo.save(company);
	}
	
	public void updateCompany(Company company) {
		companyRepo.save(company);
	}
	
	public void deleteCompany(String companyName) {
		companyRepo.deleteById(companyName);
	}
	
}
