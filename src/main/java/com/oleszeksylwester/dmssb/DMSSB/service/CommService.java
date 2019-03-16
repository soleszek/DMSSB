package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.model.NotificationsNumber;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.security.Principal;

public class CommService {
    private static SimpMessagingTemplate template;

    public static void setTemplate(SimpMessagingTemplate tmplt) {
        template = tmplt;
    }

    public static void send(String username, String message){
        template.convertAndSendToUser(username,"/queue/notify", new NotificationsNumber(message));
    }
}
