package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Document extends DMSObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int revision;
    private String type;
    private String title;
    private String description;
    private String owner;
    private LocalDate lastModification;
    private String link;

    public Document() {
    }

    public Document(String name, String state, LocalDate creadtionDate, String comments, int revision, String type, String title, String description, String owner, LocalDate creationDate, LocalDate lastModification, String link) {
        super(name, state, creationDate, comments);
        this.revision = revision;
        this.type = type;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.lastModification = lastModification;
        this.link = link;
    }

    public Long getId() {
        return id;
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

    public String getOwner() {
        return owner;
    }

    public LocalDate getLastModification() {
        return lastModification;
    }

    public String getLink() {
        return link;
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

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setLastModification(LocalDate lastModification) {
        this.lastModification = lastModification;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
