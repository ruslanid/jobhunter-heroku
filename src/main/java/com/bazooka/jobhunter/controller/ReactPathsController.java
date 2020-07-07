package com.bazooka.jobhunter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReactPathsController {
    @RequestMapping( method = {RequestMethod.GET}, path = {"/jobs/**", "/profile", "/sign-in", "/sign-up"} )
    public String redirect() {
        return "forward:/";
    } 
}
