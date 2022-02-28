package com.endava.rest.controller;

import com.endava.rest.models.Employee;
import com.endava.rest.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @RequestMapping("")
    public List<Employee> getEmployees(@RequestParam(name = "firstName", required = false) String firstName) {
        if (firstName != null) {
            return EmployeeService.getEmployeeListByFirstName(firstName);
        }
        return EmployeeService.getEmployeesList();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer id) {
        return EmployeeService.getEmployeeById(id);
    }

    @PostMapping()
    public ResponseEntity<Employee> addAnEmployee(@RequestBody Employee employee) {
        return EmployeeService.addAnEmployee(employee);
    }

    @RequestMapping("/name/{id}")
    public String getFullName(@PathVariable(value = "id") Integer id) {
        return EmployeeService.getEmployeeCompleteNameById(id);
    }

    @RequestMapping (value = "/find/lastName", headers="Content-Type=text/plain", method = RequestMethod.GET)
    public ResponseEntity<Employee> findEmployeeByLastName(@RequestBody String lastName) {
        return EmployeeService.getEmployeeByLastName(lastName);
    }
}
