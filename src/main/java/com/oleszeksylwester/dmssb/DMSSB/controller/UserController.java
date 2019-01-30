package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/dashboard")
    private String showDashboard(){
        return "dashboard";
    }

    @GetMapping("/login")
    private String showLogin(){
        return "login";
    }

    @GetMapping("/adminpanel")
    private String showAdminPanel(){
        return "adminpanel";
    }

    @GetMapping("/registration")
    private String showRegistration(Model model){
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping(value = "/registerUser")
    private ModelAndView registerUser(@ModelAttribute User user, Model model){

        userServiceImpl.saveOrUpdate(user);

        ModelAndView mov = new ModelAndView();
        mov.addObject(user);
        mov.setViewName("user");

        return mov;

    }
}
