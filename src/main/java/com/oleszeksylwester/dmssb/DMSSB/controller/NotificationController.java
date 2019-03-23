package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.NotificationsNumber;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationController {
/*
    @Autowired
    SimpMessagingTemplate template;

    @SendTo("/topic/user")
    public NotificationsNumber sentMessage(User user){
        return new NotificationsNumber("Hello");
    }*/

    @GetMapping("/notifications")
    private String showLogin() {
        return "notifications";
    }
}
