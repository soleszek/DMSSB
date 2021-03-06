package com.oleszeksylwester.dmssb.DMSSB.model.notification;

import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.observer.ObserverUser;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("notification_task")
public class NotificationTask extends Notification {
    @ManyToOne(optional = true)
    @JoinColumn(name="task_id")
    private Task task;

    public NotificationTask() {
    }

    public NotificationTask(Task task){
        this.task = task;
    }

    public NotificationTask(String name, User user, Task task){
        this.task = task;
    }

    @PrePersist
    protected void onCreate(){
        super.creationDate = LocalDate.now();
    }

    public String getType() {
        return "task";
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
