package com.mk.spring.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleRestController {
    @GetMapping("another")
    public String getAnotherMessage(){
        return "Another Message";
    }
}
