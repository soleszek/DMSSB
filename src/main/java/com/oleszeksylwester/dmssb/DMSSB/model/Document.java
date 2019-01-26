package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("document")
public class Document extends DMSObject {

    private int revision;
    private String type;
    private String title;
    private String owner;
    private String link;

    public Document() {
    }

    public Document(String name, String state, LocalDate creationDate, LocalDate lastModification, String comments, int revision, String type, String title, String owner, String link) {
        super(name, state, creationDate, lastModification, comments);
        this.revision = revision;
        this.type = type;
        this.title = title;
        this.owner = owner;
        this.link = link;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
