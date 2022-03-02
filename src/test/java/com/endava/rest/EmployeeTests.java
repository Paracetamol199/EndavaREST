package com.endava.rest;

import com.endava.rest.exception.EmployeeErrorInfo;
import com.endava.rest.exception.EmployeeResponseErrorHandler;
import com.endava.rest.factory.HttpComponentsClientHttpRequestFactoryForGetWithBody;
import com.endava.rest.models.Employee;
import org.junit.Test;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static com.endava.rest.utils.Variables.HOSTNAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class EmployeeTests {

    private static final String ENDPOINT = "/employee/";

    @Test
    public void ShouldReturnEmployeeWithId2() {
        RestTemplate restTemplate = new RestTemplate();

        Integer employeeId = 2;
        Employee employee = restTemplate.getForObject(HOSTNAME + ENDPOINT + employeeId, Employee.class);

        assertThat(employee.getId(), is(employeeId));
        assertThat(employee.getFirstName(), is("Ion"));
        assertThat(employee.getLastName(), is("Popescu"));
    }

    @Test
    public void ShouldReturnAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Employee[]> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        Employee[].class
                );

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
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
                        httpEntity,
                        Employee.class
                );
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(responseEntity.getBody().getId(), is(id));
        assertThat(responseEntity.getBody().getLastName(), is(lastName));
        assertThat(responseEntity.getBody().getFirstName(), is(firstName));
    }

    @Test
    public void ShouldReturnEmployeeCompleteName() {
        RestTemplate restTemplate = new RestTemplate();

        int employeeId = 1;

        String responseEntity = restTemplate.getForObject(HOSTNAME + ENDPOINT + "name/" + employeeId, String.class);
        assertThat(responseEntity, is("John Doe"));
    }

    @Test
    public void ShouldReturnEmployeeByUsingLastName() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactoryForGetWithBody());

        String employeeLastName = "Doe";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> request = new HttpEntity<>(employeeLastName, headers);
        ResponseEntity<Employee> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT + "find/lastName/",
                        HttpMethod.GET,
                        request,
                        Employee.class
                );

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getId(),is(1));
        assertThat(responseEntity.getBody().getFirstName(), is("John"));
        assertThat(responseEntity.getBody().getLastName(),is("Doe"));
    }

    @Test
    public void ShouldReturnExceptionMessageWhenEmployeeIdIsNotFound() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new EmployeeResponseErrorHandler());

        Integer employeeId = 66;
        ResponseEntity<EmployeeErrorInfo> exception = restTemplate.getForEntity(HOSTNAME + ENDPOINT + employeeId, EmployeeErrorInfo.class);

        assertThat(exception.getBody().getMessage(), is("Nu am putut sa gasim un angajat dupa ID-ul introdus"));
        assertThat(exception.getBody().getHttpStatus(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void ShouldReturnExceptionMessageWhenEmployeeLastNameIsNotFound() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactoryForGetWithBody());
        restTemplate.setErrorHandler(new EmployeeResponseErrorHandler());

        String employeeLastName = "Valeriu";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> request = new HttpEntity<>(employeeLastName, headers);
        ResponseEntity<EmployeeErrorInfo> responseEntity =
                restTemplate.exchange(
                        HOSTNAME + ENDPOINT + "find/lastName/",
                        HttpMethod.GET,
                        request,
                        EmployeeErrorInfo.class);

        assertThat(responseEntity.getBody().getMessage(), is("Nu am putut sa gasim un angajat dupa numele introdus"));
        assertThat(responseEntity.getBody().getHttpStatus(), is(HttpStatus.NOT_FOUND));
    }

}
