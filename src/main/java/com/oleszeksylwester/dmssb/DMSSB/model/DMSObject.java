package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class DMSObject {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String state;
    private LocalDate creationDate;
    private String comments;

    public DMSObject() {
    }

    public DMSObject(String name, String state, LocalDate creationDate, String comments) {
        this.name = name;
        this.state = state;
        this.creationDate = creationDate;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
