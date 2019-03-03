package com.oleszeksylwester.dmssb.DMSSB.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("message_received")
public class MessageReceived extends Message {
    public MessageReceived() {
    }

    public MessageReceived(String name, String title, String content, User sender, User receiver, LocalDate sendingDate, LocalDate receivingDate, boolean isRead, boolean isDeleted) {
        super(name, title, content, sender, receiver, sendingDate, receivingDate, isRead, isDeleted);
    }
}
