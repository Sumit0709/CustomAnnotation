package com.sumit.tcs.CustomAnnotation.controller;

import com.sumit.tcs.CustomAnnotation.dao.ByeMessage;
import com.sumit.tcs.CustomAnnotation.dao.WelcomeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    WelcomeMessage welcome;

    @Autowired
    ByeMessage bye;

    @GetMapping("/welcome")
    public String welcomeMessage(){
        System.out.println(welcome.getMessage());
        return welcome.getMessage();
    }

    @GetMapping("/bye")
    public String byeMessage(){
        System.out.println(bye.getMessage());
        return bye.getMessage();
    }
}
