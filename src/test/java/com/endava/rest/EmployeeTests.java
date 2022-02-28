package com.endava.rest;

import com.endava.rest.models.Employee;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.endava.rest.utils.Temp.HOSTNAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class EmployeeTests {
    private static final String ENDPOINT = "/employee";

    @Test
    public void ShouldReturnEmployeeWithId2() {
        RestTemplate restTemplate = new RestTemplate();

        Integer employeeId = 2;
        Employee employee = restTemplate.getForObject(HOSTNAME + ENDPOINT +"/" + employeeId, Employee.class);

        assertThat(employee.getId(), is(employeeId));
        assertThat(employee.getFirstName(), is("Ion"));
        assertThat(employee.getLastName(), is("Popescu"));
    }

    @Test
    public void ShouldReturnAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Employee[]> responseEntity =
                restTemplate.exchange(
                        HOSTNAME+ENDPOINT,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        Employee[].class
                );

        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        assertThat(responseEntity.getBody().length, is(greaterThan(1)));
    }

    @Test
    public void ShouldBeAbleToCreateAnEmployee() {
        RestTemplate restTemplate = new RestTemplate();

        Integer id = 3;
        String firstName = "Ionut";
        String lastName = "Popescu";

        Employee newEmployee = new Employee(id, firstName, lastName);
        HttpEntity<Employee> httpEntity = new HttpEntity<>(newEmployee);
        ResponseEntity<Employee> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT,
                        HttpMethod.POST,
                        HttpEntity.EMPTY,
                        Employee.class
                );

        assertThat(responseEntity.getStatusCode(),is(HttpStatus.CREATED));
        assertThat(responseEntity.getBody().getId(), is(id));
        assertThat(responseEntity.getBody().getLastName(), is(lastName));
        assertThat(responseEntity.getBody().getFirstName(), is(firstName));
    }
}
