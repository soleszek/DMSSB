package com.oleszeksylwester.dmssb.DMSSB.observer;

import com.oleszeksylwester.dmssb.DMSSB.model.User;

public interface ObserverUser {
    void addUser(User user);
    void removeUser(User user);
    void updateTask(long taskId);
    void updateMessage(long messageId);
}
