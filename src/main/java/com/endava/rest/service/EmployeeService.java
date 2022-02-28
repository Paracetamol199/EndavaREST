package com.endava.rest.service;

import com.endava.rest.exception.ExmployeeResourceException;
import com.endava.rest.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

    private static List<Employee> employees = new ArrayList<Employee>() {
        {
            add(new Employee(1, "John", "Doe"));
            add(new Employee(2, "Ion", "Popescu"));
        }
    };

    public static List<Employee> getEmployeesList() {
        return employees;
    }

    public static List<Employee> getEmployeeListByFirstName(String firstName) {
        return employees
                .stream()
                .filter(employee -> employee.getFirstName().equals(firstName))
                .collect(Collectors.toList());
    }

    public static ResponseEntity<Employee> getEmployeeById(Integer id) {
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseThrow(() -> new ExmployeeResourceException("Nu am putut sa gasim un angajat dupa ID-ul introdus"));
    }

    public static ResponseEntity<Employee> addAnEmployee(Employee employee) {
        employees.add(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    public static String getEmployeeCompleteNameById(Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .orElse("Soldatul Pierdut");
    }

    public static ResponseEntity<Employee> getEmployeeByLastName(String lastName) {
        return employees
                .stream()
                .filter(employee -> employee.getLastName().equals(lastName))
                .findFirst()
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseThrow(() -> new ExmployeeResourceException("Nu am putut sa gasim un angajat dupa numele introdus"));
    }
}
