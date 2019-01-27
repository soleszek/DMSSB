package com.oleszeksylwester.dmssb.DMSSB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
    @RequestMapping("/viewer")
    private String showViewer(){
        return "viewer";
    }

    @RequestMapping(value = {"/dashboard"})
    private String showDashboard(){
        return "dashboard";
    }

    @RequestMapping("/login")
    private String showLogin(){
        return "login";
    }

    @RequestMapping("/adminpanel")
    private String showAdminPanel(){
        return "adminpanel";
    }

    @RequestMapping("/registration")
    private String showRegistration(){
        return "registration";
    }
}
