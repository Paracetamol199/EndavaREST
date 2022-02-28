package com.endava.rest.models;

import org.springframework.stereotype.Component;

@Component
public class Employee {

    private String firstName;
    private String lastName;
    private Integer id;

    public Employee() {

    }

    public Employee(Integer id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }
}
