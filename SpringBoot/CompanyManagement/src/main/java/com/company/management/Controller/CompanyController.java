package com.company.management.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.company.management.Model.db.Company;
import com.company.management.Services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/get/{companyName}")
	public Company getCompany(@PathVariable String companyName) {

		Company company = companyService.getCompany(companyName);
		return company;
	}

	@GetMapping("/get")
	public List<Company> getCompanies() {

		List<Company> companyList = new ArrayList<Company>();
		Iterable<Company> companies = companyService.getCompanies();
		companies.forEach(company -> companyList.add(company));
		return companyList;
	}

	@PostMapping("/add")
	public Company addCompany(@RequestBody Company company) {
		companyService.addCompany(company);
		return company;
	}

	@PutMapping("/update/{companyName}")
	public Company updateCompany(@RequestBody Company company) {
		companyService.updateCompany(company);
		return company;
	}

	@DeleteMapping("/delete/{companyName}")
	public void deleteCompany(@PathVariable String companyName) {
		companyService.deleteCompany(companyName);
	}

}
