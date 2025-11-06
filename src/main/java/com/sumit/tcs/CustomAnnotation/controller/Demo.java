package com.sumit.tcs.CustomAnnotation.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class Demo {

    @PostConstruct
    public void postConstructRun(){
        System.out.println("Printing post construct :: " + this.getClass());
    }

}
