package mithun.java.prep.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Employee {
    String name;
    String designation;
    int age;
    int empId;
    int salary;
    List<Integer> phoneNumbers;


}
