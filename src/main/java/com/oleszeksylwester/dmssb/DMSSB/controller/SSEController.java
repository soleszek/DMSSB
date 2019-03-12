package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class SSEController {

    private static final Logger LOGGER = Logger.getLogger(SSEController.class.getName());

    private NotificationService notificationService;

    @Autowired
    public SSEController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notification")
    public Flux<List<String>> getNotification(){
        List<Notification> notifications = notificationRepository.findByUsername(user);
        return notificationService.getNotifications(notifications);
    }
}
