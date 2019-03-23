package com.oleszeksylwester.dmssb.DMSSB.config;

import com.oleszeksylwester.dmssb.DMSSB.model.NotificationsNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class SchedulerConfig {
/*
    @Autowired
    SimpMessagingTemplate template;

    *//*@Scheduled(fixedDelay = 3000)
    public void sendAdhocMessages() {
        template.convertAndSend("/notifications/user", new NotificationsNumber("Fixed Delay Scheduler"));
    }*//*

    public void sendAdHocMessages(){
        for(int i = 0; i <= 10; i++){
            template.convertAndSend("/topic/user", new NotificationsNumber("Numer:"));
        }
    }*/
}
