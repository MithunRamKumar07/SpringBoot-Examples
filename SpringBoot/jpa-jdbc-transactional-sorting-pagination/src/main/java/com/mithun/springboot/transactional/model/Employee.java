package com.mithun.springboot.transactional.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "age")
    int age;
    @Id
    @GeneratedValue
    @JsonProperty(value = "employeeId")
    int employeeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="EMPLOYEE_ID")
    @JsonProperty(value = "employeeCompensation")
    EmployeeCompensation employeeCompensation;
//
//    @OneToMany(mappedBy = "EMPLOYEE")
//    @JoinColumn(name="EMPLOYEE_ID")
//    @JsonProperty(value = "bankAccounts")
//    List<BankAccount> bankAccounts;
}
