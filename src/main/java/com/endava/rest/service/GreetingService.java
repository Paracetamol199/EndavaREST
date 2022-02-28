package com.endava.rest.service;

import com.endava.rest.models.Greeting;

public class GreetingService {

    private static Greeting greetingMessage = new Greeting();

    public static String getGreetingMessage(String name) {
        greetingMessage.setMessage("Hello, ");
        return greetingMessage.getMessage() + name;
    }
}
