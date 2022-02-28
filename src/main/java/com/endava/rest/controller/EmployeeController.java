package com.endava.rest.controller;

import com.endava.rest.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static List<Employee> employees = new ArrayList<Employee>() {
        {
            add(new Employee(1, "John", "Doe"));
            add(new Employee(2, "Ion", "Popescu"));
        }
    };

    //    @RequestMapping("")
//    public List<Employee> getEmployees() {
//        return employees;
//    }
    @RequestMapping("")
    public List<Employee> getEmployees(@RequestParam(name = "firstName", required = false) String firstName) {
        if (firstName != null) {
            return employees
                    .stream()
                    .filter(employee -> employee.getFirstName().equals(firstName))
                    .collect(Collectors.toList());
        }

        return employees;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer id) {
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> addAnEmployee(@RequestBody Employee employee) {
        employees.add(employee);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @RequestMapping("/name/{id}")
    public String getFullName(@PathVariable(value = "id") Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .orElse( "Soldatul Pierdut");
    }

    @RequestMapping()
    public String findEmployee
    //ULTRA OPTIONALA
    // pentru findEmployees
 //   https://stackoverflow.com/questions/32441919/how-return-error-message-in-spring-mvc-controller
}
