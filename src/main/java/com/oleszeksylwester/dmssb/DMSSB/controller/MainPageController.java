package com.oleszeksylwester.dmssb.DMSSB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
/*    @RequestMapping("/public")
    private String showPublic(){
        return "public";
    }*/

    @RequestMapping("/viewer")
    private String showViewr(){
        return "viewer";
    }

    @RequestMapping(value = {"/dashboard"})
    private String showPrivate(){
        return "dashboard";
    }

    @RequestMapping("/login")
    private String showLogin(){
        return "login";
    }
}
