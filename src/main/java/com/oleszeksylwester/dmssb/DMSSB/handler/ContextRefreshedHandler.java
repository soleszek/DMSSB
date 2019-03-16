package com.oleszeksylwester.dmssb.DMSSB.handler;

import com.oleszeksylwester.dmssb.DMSSB.service.CommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ContextRefreshedHandler implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = Logger.getLogger(ContextRefreshedHandler.class.getName());

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            CommService.setTemplate(template);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Klasa " + getClass() + " powoduje błąd " + ex);
        }
    }
}
