package com.oleszeksylwester.dmssb.DMSSB.model.notification;

import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="notification",
        discriminatorType = DiscriminatorType.STRING)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name="owner_id")
    private User user;
    LocalDate creationDate;

    public Notification() {
    }

    public Notification(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
