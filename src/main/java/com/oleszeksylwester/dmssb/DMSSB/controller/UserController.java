package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.serviceimpl.UserServiceImpl;
import com.oleszeksylwester.dmssb.DMSSB.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @GetMapping("/dashboard")
    private String showDashboard() {
        return "dashboard";
    }

    @GetMapping("/login")
    private String showLogin() {
        return "login";
    }

    @GetMapping("/adminpanel")
    private String showAdminPanel() {
        return "adminpanel";
    }

    @GetMapping("/registration")
    private String showRegistration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping(value = "/registerUser")
    private ModelAndView registerUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, @RequestParam String role) {

        ModelAndView mov = new ModelAndView();

        User existUser = userServiceImpl.findByUsername(user.getUsername());

        if (existUser != null) {
            bindingResult.rejectValue("username", "error.user", "User with this login already exist");
            mov.setViewName("registration");

            return mov;
        }

        if (bindingResult.hasErrors()) {
            mov.setViewName("registration");

            return mov;

        } else {

            userServiceImpl.saveOrUpdate(user, role);

            mov.addObject("user", user);
            mov.setViewName("user");

            return mov;
        }

    }

    @GetMapping(value = "/displayUserDetails")
    private ModelAndView displayUserDetails(){
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userServiceImpl.findByUsername(username);

        mov.addObject(user);
        mov.setViewName("user");

        return mov;
    }

    @GetMapping(value = "/all/users")
    private ModelAndView displayAllUsers(){
        ModelAndView mov = new ModelAndView();

        List<User> users;

        users = userServiceImpl.findAll();

        mov.addObject("users", users);
        mov.setViewName("allusers");

        return mov;
    }
}
