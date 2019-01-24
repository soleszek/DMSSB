package com.oleszeksylwester.dmssb.DMSSB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/public")
    private String showPublic(){
        return "public";
    }

    @RequestMapping("/viewer")
    private String showViewr(){
        return "viewer";
    }

    @RequestMapping("/private")
    private String showPrivate(){
        return "private";
    }

    @RequestMapping("/login")
    private String showLogin(){
        return "login";
    }
}
