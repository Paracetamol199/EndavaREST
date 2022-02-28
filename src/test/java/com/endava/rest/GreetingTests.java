package com.endava.rest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static com.endava.rest.utils.Temp.HOSTNAME;
import static org.hamcrest.CoreMatchers.is;

public class GreetingTests {
    private static final String ENDPOINT = "/greeting";

    @Test
    public void ShouldReturnDefaultGreetingsWhenNoNameIsGiven() {
        RestTemplate restTemplate = new RestTemplate();

        String message = restTemplate.getForObject(HOSTNAME + ENDPOINT, String.class);

        Assert.assertThat(message, is("Hello World"));
    }

    @Test
    public void ShouldReturnGreetingsWhenNameIsGiven() {
        RestTemplate restTemplate = new RestTemplate();

        String message = restTemplate.getForObject(HOSTNAME + ENDPOINT + "?name=Ciprian", String.class);

        Assert.assertThat(message, is("Hello Ciprian"));
    }
}
