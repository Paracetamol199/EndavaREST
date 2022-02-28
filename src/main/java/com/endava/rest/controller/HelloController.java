package com.endava.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final String templateGreeting = "Hello, %s";

    @RequestMapping("/greeting")
    public String hello(
            @RequestParam(name = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }
}

