package com.mithun.springboot.transactional.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYEE_COMPENSATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCompensation {

    @Id
    @GeneratedValue
    @JsonProperty(value = "compensationId")
    int compensationId;
    @JsonProperty(value = "employeeIdComp")
    int employeeId;
    @JsonProperty(value = "salary")
    int salary;
    @JsonProperty(value = "costToTheCompany")
    int costToTheCompany;

}
