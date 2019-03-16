package com.oleszeksylwester.dmssb.DMSSB.model.notification;

import com.oleszeksylwester.dmssb.DMSSB.model.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("notification_task")
public class NotificationTask extends Notification {
    private final String type = "task";

    public NotificationTask() {
    }

    public NotificationTask(User user){
        super(user);
    }

    @PrePersist
    protected void onCreate(){
        super.creationDate = LocalDate.now();
    }
}
