package com.mk.spring.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleRestController {
    @GetMapping("/user")
    public User getAnotherMessage(){
       User user = new User();
       user.setId(1);
       user.setName("Newgen");
       user.setEmail("contactus@newgen.com");

       return user;
    }
}
