package com.assignments.org.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewController {

    @GetMapping(value="/hello")
    public String getHello(){
        return "Hello Controller";
    }

    @GetMapping(value="/bye")
    public String getBye(){
        return "Bye Controller";
    }
}
