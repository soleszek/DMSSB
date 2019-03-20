package com.oleszeksylwester.dmssb.DMSSB.service.notification;

import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.NotificationsNumber;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.model.message.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.Notification;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.NotificationMessage;
import com.oleszeksylwester.dmssb.DMSSB.observer.SubjectNotification;
import com.oleszeksylwester.dmssb.DMSSB.repository.NotificationRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationMessageService implements SubjectNotification {

    private final NotificationRepository notificationRepository;
    private NameFactory nameFactory;
    private final UserRepository userRepository;

    @Autowired
    public NotificationMessageService(NotificationRepository notificationRepository, NameFactory nameFactory, UserRepository userRepository){
        this.notificationRepository = notificationRepository;
        this.nameFactory = nameFactory;
        this.userRepository = userRepository;
    }

    public Notification saveOrUpdate(Message message, User user){

        Notification notificationMessage = new NotificationMessage(message);
        notificationMessage.setUser(user);

        notificationRepository.save(notificationMessage);

        Long notificationId = notificationMessage.getId();
        String name = nameFactory.createName(notificationId, ((NotificationMessage) notificationMessage).getType());
        notificationMessage.setName(name);

        notificationRepository.save(notificationMessage);

        findAndNotifyObserver(notificationMessage.getUser());

        return null;
    }

    @Override
    public void findAndNotifyObserver(User user) {
        User userToNotify =  userRepository.findByUsername(user.getUsername());
        List<Notification> notifications = notificationRepository.findAllByUser(user);
        String number = String.valueOf(notifications.size());
        NotificationsNumber notificationsNumber = new NotificationsNumber();
        notificationsNumber.setContent(number);
        userToNotify.update(userToNotify, notificationsNumber);
    }
}
