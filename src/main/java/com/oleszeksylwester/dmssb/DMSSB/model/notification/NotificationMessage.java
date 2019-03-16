package com.oleszeksylwester.dmssb.DMSSB.model.notification;

import com.oleszeksylwester.dmssb.DMSSB.model.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("notification_message")
public class NotificationMessage extends Notification {
    private final String type = "message";

    public NotificationMessage(User user) {
        super(user);
    }

    @PrePersist
    protected void onCreate(){
        super.creationDate = LocalDate.now();
    }
}
