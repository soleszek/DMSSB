package com.oleszeksylwester.dmssb.DMSSB.model.message;

import com.oleszeksylwester.dmssb.DMSSB.model.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("message_sent")
public class MessageSent extends Message {
    public MessageSent() {
    }

    public MessageSent(String name, String title, String content, User sender, User receiver, LocalDate sendingDate, LocalDate receivingDate, boolean isRead, boolean isDeleted) {
        super(name, title, content, sender, receiver, sendingDate, receivingDate, isRead, isDeleted);
    }


}
