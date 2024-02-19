package com.mithun.springboot.transactional.dao;

import com.mithun.springboot.transactional.model.Employee;
import com.mithun.springboot.transactional.model.EmployeeCompensation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setName(rs.getString("NAME"));
        employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
        employee.setAge(rs.getInt("AGE"));
        employee.setEmployeeCompensation(getEmployeeCompensation(rs));
        return employee;
    }

    private static EmployeeCompensation getEmployeeCompensation(ResultSet rs) throws SQLException {
        EmployeeCompensation employeeCompensation = new EmployeeCompensation();
        employeeCompensation.setCompensationId(rs.getInt("COMPENSATION_ID"));
        employeeCompensation.setSalary(rs.getInt("SALARY"));
        employeeCompensation.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
        employeeCompensation.setCostToTheCompany(rs.getInt("COST_TO_THE_COMPANY"));
        return employeeCompensation;
    }
}
