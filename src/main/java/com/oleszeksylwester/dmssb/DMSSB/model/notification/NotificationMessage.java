package com.oleszeksylwester.dmssb.DMSSB.model.notification;

import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.model.message.Message;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("notification_message")
public class NotificationMessage extends Notification {
    @ManyToOne(optional = true)
    @JoinColumn(name="message_id")
    private Message message;

    public NotificationMessage(){
    }

    public NotificationMessage(Message message){
        this.message = message;
    }

    public NotificationMessage(String name, User user, Message message) {
        super(name, user);
        this.message = message;
    }

    @PrePersist
    protected void onCreate(){
        super.creationDate = LocalDate.now();
    }

    public String getType(){
        return "message";
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
