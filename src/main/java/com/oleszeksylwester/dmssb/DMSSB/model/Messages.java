package com.oleszeksylwester.dmssb.DMSSB.model;

import java.util.List;

public class Messages {
    private List<Message> messagesList;

    public Messages() {
    }

    public Messages(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public List<Message> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Message> messagesList) {
        this.messagesList = messagesList;
    }
}
