package com.oleszeksylwester.dmssb.DMSSB.enums;

public enum NotificationTypes {
    TASK("task"),
    MESSAGE("message");

    String notificationType;

    NotificationTypes(String notificationType){
        this.notificationType = notificationType;
    }

    public String getNotificationType() {
        return notificationType;
    }
}
