package com.oleszeksylwester.dmssb.DMSSB.model.notification;

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
    @ManyToOne(optional = false)
    @JoinColumn(name="owner_id")
    private User user;
    LocalDate creationDate;

    public Notification() {
    }

    public Notification(User user) {
        this.user = user;
    }
}
