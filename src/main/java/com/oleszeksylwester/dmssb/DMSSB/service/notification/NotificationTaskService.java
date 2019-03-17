package com.oleszeksylwester.dmssb.DMSSB.service.notification;

import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.Notification;
import com.oleszeksylwester.dmssb.DMSSB.model.notification.NotificationTask;
import com.oleszeksylwester.dmssb.DMSSB.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationTaskService {

    private final NotificationRepository notificationRepository;
    private NameFactory nameFactory;

    @Autowired
    public NotificationTaskService(NotificationRepository notificationRepository, NameFactory nameFactory){
        this.notificationRepository = notificationRepository;
        this.nameFactory = nameFactory;
    }

    public Notification saveOrUpdate(Task task, User user){

        Notification notificationTask = new NotificationTask(task);
        notificationTask.setUser(user);

        notificationRepository.save(notificationTask);

        Long notificationId = notificationTask.getId();
        String name = nameFactory.createName(notificationId, ((NotificationTask) notificationTask).getType());
        notificationTask.setName(name);

        notificationRepository.save(notificationTask);


        return null;
    }
}
