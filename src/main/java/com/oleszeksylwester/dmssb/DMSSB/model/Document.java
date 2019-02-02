package com.oleszeksylwester.dmssb.DMSSB.model;

import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int revision;
    private String type;
    private String title;
    private String description;
    private String state;
    private User owner;
    private LocalDate creationDate;
    private LocalDate lastModification;
    private String link;

    public Document() {
    }

    public Document(String name, int revision, String type, String title, String description, String state, User owner, LocalDate creationDate, LocalDate lastModification, String link) {
        this.name = name;
        this.revision = revision;
        this.type = type;
        this.title = title;
        this.description = description;
        this.state = state;
        this.owner = owner;
        this.creationDate = creationDate;
        this.lastModification = lastModification;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRevision() {
        return revision;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public User getOwner() {
        return owner;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getLastModification() {
        return lastModification;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastModification(LocalDate lastModification) {
        this.lastModification = lastModification;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
