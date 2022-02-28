package com.endava.rest.controller;

import com.endava.rest.service.GreetingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class HelloController {

    @RequestMapping("")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return GreetingService.getGreetingMessage(name);
    }
}

