package com.spring.example.myfirstdemo.controller;

import com.spring.example.myfirstdemo.domain.details;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;
@RestController
public class MyuserController {

    @GetMapping("/hellodevopscontents")
    public String getNames() {
        return ( "Welcome to Dockers and K8S, Jenkins GIT");
    }

    @GetMapping("/hellowelcometeam")
    public String getGreetings() {
        return ( "Welcome to pg devops folks and K8S team");
    }

    @GetMapping("/getteamdetails")
    public List<details> getTeamDetails() {
        List<details> kanhadetails = new ArrayList<>();
         details d1 = new details( "Venkata" , "Devops");
         details d2 = new details( "Vignesh" , "Devops");
         details d3 = new details( "Abhijith" , "Devops");
         details d4 = new details( "Vivek" , "Devops");
         details d5 = new details( "Radha" , "Devops");
         kanhadetails.add(d1);
         kanhadetails.add(d2);
         return kanhadetails;

    }
}
