package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

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

    @PostMapping(value = "/registerUser")
    private String registerUser(User user, Model model){

        return "user";

    }
}
