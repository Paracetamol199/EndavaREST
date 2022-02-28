package com.endava.rest.service;

import com.endava.rest.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private static List<Employee> employees = new ArrayList<Employee>() {
        {
            add(new Employee(1, "John", "Doe"));
            add(new Employee(2, "Ion", "Popescu"));
        }
    };

}
