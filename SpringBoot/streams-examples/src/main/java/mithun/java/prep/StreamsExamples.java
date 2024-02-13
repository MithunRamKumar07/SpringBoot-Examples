package mithun.java.prep;

import lombok.extern.slf4j.Slf4j;
import mithun.java.prep.model.Employee;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamsExamples{

    //List of Employee Strings
    List<String> employees = Arrays.asList("Mithun","Ram","Kumar","Aadhi","Vish","Arumugam","Mani");
    //List of Employees with null value for testing
    List<String> employeesWithNullValue = Arrays.asList("Mithun","Ram","Kumar","Aadhi","Vish","Arumugam","Mani",null);
    //List of Employee Objects
     List<Employee> employeeList= Arrays.asList(
                new Employee("Mithun","Junior Software Engineer",32,1,1000,Arrays.asList(41233784,23435)),
                new Employee("Ram","Senior Software Engineer",40,2,2000,Arrays.asList(412333454,324572,5678)),
                new Employee("Kumar","Software Engineer",35,3,3000,Arrays.asList(412343534,2344535)),
                new Employee("Arumugam","Lead Software Engineer",42,4,4000,Arrays.asList(412345334,23434335,324565,34456)));

    void filterExample(){
        // Filter names whose length > 5
        List<String> filteredList = employees.stream()
                .filter(employee->employee.length()>5)
                .toList();
        log.info("Filtered list : {}", filteredList);
    }

    void mapExamples(){
        //map to uppercase and print
        employees.stream()
                .map(employee-> convertToUpperCase(employee))
                .forEach(employeeName-> log.info(employeeName));
        //map to uppercase and collect
        List<String> employeeNamesInLowerCase = employees.stream()
                .map(employee-> convertToUpperCase(employee))
                .collect(Collectors.toList());
        log.info("Map and Collected List : {}",employeeNamesInLowerCase);

        //mapToInt
        log.info("**************** mapToInt() **********************");
        OptionalInt maxAge = employeeList.stream()
                .mapToInt(Employee::getAge)
                .max();
        log.info(maxAge.toString());

        //FlatMap
        //Collate elements from different lists , flat them , collect as list
        log.info("*********************** flatMap()**********************************");
        List<Integer> phoneNumbers = employeeList.stream()
                .flatMap(emp-> emp.getPhoneNumbers().stream())
                .toList();
        System.out.println(phoneNumbers);

        //print the total number of phone numbers
        long totalPhNum = employeeList
                .stream()
                .flatMap(emp-> emp.getPhoneNumbers().stream())
                .count();

        log.info("Total phone numbers : {} ",totalPhNum);

    }

    void skipExample(){
        //Skips first n objects
        log.info("************ skip () **************");
        employees.stream().skip(2)
                .forEach(emp->log.info(emp));

    }

    void functionExample(){
        //function to convert to lowercase and print
        Function <String,String> toLowerCaseFunction = input -> {
            input = input.toLowerCase();
             log.info(input);
             return input;
        };
        //convert names to lowercase and print using function interface
        employees.stream().
                forEach(employee-> toLowerCaseFunction.apply(employee));

    }

       void streamSortingExamples(){
           // Natural Order
            log.info("********** Natural Order *************");
            List sortedEmployees = employees.stream()
                .sorted()
                .peek(employee->log.info(employee))
                .collect(Collectors.toList());
           // Reverse Order
            log.info("********** Reverse Order **********");
            employees.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(emp-> log.info(emp));
           //Process the first 4 elements in the stream
           log.info("********** Limit 4 **********");
           employees.stream()
                .limit(4)
                .forEach(emp-> log.info(emp));

         //Comparator.comparing
           log.info("********** Comparator.comparing **********");
           employeeList.stream()
                   .sorted(Comparator.comparing(Employee::getAge))
                   .forEach(emp-> log.info(emp.toString()));

           //Comparator.thenComparing
           log.info("********** Comparator.thenComparing **********");
           employeeList.stream()
                   .sorted(Comparator.comparingInt(Employee::getAge).
                           thenComparing(Employee::getSalary)).
                   forEach(emp-> log.info(emp.toString()));

           //Using collections.sort
           Comparator<Employee> nameComparator = Comparator.comparing(Employee::getName);

            Collections.sort(employeeList,nameComparator);
            System.out.println("***********Sorted list using Collections.sort *********** ");
            System.out.println(employeeList);
    }

    void reduceExample(){
        log.info("************* reduce() example **********************");
        //Accumalates and sums the salary of
        int totalSalaryToEmp = employeeList.stream()
                .mapToInt(Employee::getSalary)
                .reduce(0,(salary1 ,salary2)->salary1+salary2);
        log.info("Total Salary {} ",totalSalaryToEmp );
    }

    void binaryOperatorExample(){
        log.info("************* BiFunction example **********************");

        //Different input and output types
        BiFunction<Integer,Integer,Double> agePlusSalaryFunction = (x,y) ->  x.doubleValue() + y.doubleValue();
        List<Double> agePlusSalaries = employeeList.stream()
                    .map(emp-> agePlusSalaryFunction.apply(emp.getSalary(),emp.getAge()))
                    .toList();
        System.out.println(agePlusSalaries);

        log.info("************* Binary Operator example **********************");

        //Same input and same output
        BinaryOperator<Integer> totalSalaryOperator = (x, y) -> x + y;
        List<Integer> total = employeeList.stream()
                    .map(emp-> totalSalaryOperator.apply(emp.getSalary(),emp.getAge()))
                .toList();
        System.out.println(total);

    }

    private String convertToUpperCase(String employee) {
        return employee.toUpperCase();
    }

}
