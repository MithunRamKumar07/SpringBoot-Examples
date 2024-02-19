package com.mithun.springboot.transactional.dao;

import com.mithun.springboot.transactional.model.EmployeeCompensation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeCompensationRepository extends JpaRepository<EmployeeCompensation,Integer> {
}
