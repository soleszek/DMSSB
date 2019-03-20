package com.oleszeksylwester.dmssb.DMSSB.observer;

import com.oleszeksylwester.dmssb.DMSSB.model.User;

public interface SubjectNotification {
    void findAndNotifyObserver(User user);
}
