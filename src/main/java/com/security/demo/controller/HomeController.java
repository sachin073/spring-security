package com.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/login")
    public String login(){
        System.out.printf("login intercepted");
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        System.out.printf("accessDenied intercepted");
        return "accessDenied";
    }



    @GetMapping("/home")
    public String getHome(){
        System.out.printf("login successful");
        return "home";
    }

}
