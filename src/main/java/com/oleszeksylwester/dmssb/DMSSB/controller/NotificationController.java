package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.NotificationsNumber;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.Notification;
import com.oleszeksylwester.dmssb.DMSSB.repository.NotificationRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.notification.NotificationTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    SimpMessagingTemplate template;
    UserRepository userRepository;
    NotificationRepository notificationRepository;

    /*
    @SendTo("/topic/user")
    public NotificationsNumber sentMessage(User user){
        return new NotificationsNumber("Hello");
    }*/

    @MessageMapping("/queue/notify")
    @SendTo("/queue/notify")
    public NotificationsNumber getUser(User user) {

        User userToNotify =  userRepository.findByUsername(user.getUsername());
        List<Notification> notifications = notificationRepository.findAllByUser(user);
        String number = String.valueOf(notifications.size());
        NotificationsNumber notificationsNumber = new NotificationsNumber();
        notificationsNumber.setContent(number);

        return new NotificationsNumber(notificationsNumber.toString());
    }

    @GetMapping("/notifications")
    private String showLogin() {
        return "notifications";
    }
}
