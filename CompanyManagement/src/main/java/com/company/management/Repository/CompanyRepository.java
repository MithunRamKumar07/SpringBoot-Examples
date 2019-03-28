package com.company.management.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.management.Model.db.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company,String>{
	
}
