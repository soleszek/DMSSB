package com.oleszeksylwester.dmssb.DMSSB.service.notification;

import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.model.message.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.Notification;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.NotificationMessage;
import com.oleszeksylwester.dmssb.DMSSB.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationMessageService {

    private final NotificationRepository notificationRepository;
    private NameFactory nameFactory;

    @Autowired
    public NotificationMessageService(NotificationRepository notificationRepository, NameFactory nameFactory){
        this.notificationRepository = notificationRepository;
        this.nameFactory = nameFactory;
    }

    public Notification saveOrUpdate(Message message, User user){

        Notification notificationMessage = new NotificationMessage(message);
        notificationMessage.setUser(user);

        notificationRepository.save(notificationMessage);

        Long notificationId = notificationMessage.getId();
        String name = nameFactory.createName(notificationId, ((NotificationMessage) notificationMessage).getType());
        notificationMessage.setName(name);

        notificationRepository.save(notificationMessage);

        return null;
    }
}
