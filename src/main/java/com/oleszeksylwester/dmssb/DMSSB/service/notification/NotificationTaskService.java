package com.oleszeksylwester.dmssb.DMSSB.service.notification;

import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.NotificationsNumber;
import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.Notification;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.NotificationTask;
import com.oleszeksylwester.dmssb.DMSSB.observer.ObserverUser;
import com.oleszeksylwester.dmssb.DMSSB.observer.SubjectNotification;
import com.oleszeksylwester.dmssb.DMSSB.repository.NotificationRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTaskService implements SubjectNotification {

    private final NotificationRepository notificationRepository;
    private NameFactory nameFactory;
    private final UserRepository userRepository;

    @Autowired
    public NotificationTaskService(NotificationRepository notificationRepository, NameFactory nameFactory, UserRepository userRepository){
        this.notificationRepository = notificationRepository;
        this.nameFactory = nameFactory;
        this.userRepository = userRepository;
    }

    public Notification saveOrUpdate(Task task, User user){

        Notification notificationTask = new NotificationTask(task);
        notificationTask.setUser(user);

        notificationRepository.save(notificationTask);

        Long notificationId = notificationTask.getId();
        String name = nameFactory.createName(notificationId, ((NotificationTask) notificationTask).getType());
        notificationTask.setName(name);

        notificationRepository.save(notificationTask);

        findAndNotifyObserver(notificationTask.getUser());

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
