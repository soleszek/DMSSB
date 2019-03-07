package com.oleszeksylwester.dmssb.DMSSB.model;

import java.util.List;

public class MessageLists {
    List<MessageReceived> messagesReceived;
    List<MessageSent> messagesSent;

    public MessageLists() {
    }

    public List<MessageReceived> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<MessageReceived> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public List<MessageSent> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<MessageSent> messagesSent) {
        this.messagesSent = messagesSent;
    }
}
