package com.mithun.springboot.transactional.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDto {
    @JsonProperty("employees")
    List<Employee> employees;

}
