package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String owner;
    private String assignedTo;
    private String documentBeingApprovedId;
    private String documentBeingApprovedName;
    private String state; //Active, Completed
    private LocalDate dueDate;
    private LocalDate completionDate;
    private String comments;
    private String parentId;

    public Task(){

    }

    public static class Builder {
        private String name;
        private String owner;
        private String assignedTo;
        private String documentBeingApprovedId;
        private String documentBeingApprovedName;
        private String state;
        private LocalDate dueDate;
        private LocalDate completionDate;
        private String comments;
        private String parentId;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder owner (String owner){
            this.owner = owner;
            return this;
        }

        public Builder assignedTo (String assignedTo){
            this.assignedTo = assignedTo;
            return this;
        }

        public Builder documentBeingApprovedId(String documentBeingApprovedId){
            this.documentBeingApprovedId = documentBeingApprovedId;
            return this;
        }

        public Builder documentBeingApprovedName(String documentBeingApprovedName){
            this.documentBeingApprovedName = documentBeingApprovedName;
            return this;
        }

        public Builder state (String state) {
            this.state = state;
            return this;
        }

        public Builder dueDate (LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder completionDate (LocalDate completionDate) {
            this.completionDate = completionDate;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder parentId(String parentId) {
            this.parentId = parentId;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    private Task (Builder builder){
        this.name = builder.name;
        this.owner = builder.owner;
        this.assignedTo = builder.assignedTo;
        this.documentBeingApprovedId = builder.documentBeingApprovedId;
        this.documentBeingApprovedName = builder.documentBeingApprovedName;
        this.state = builder.state;
        this.dueDate = builder.dueDate;
        this.completionDate = builder.completionDate;
        this.comments = builder.comments;
        this.parentId = builder.parentId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getDocumentBeingApprovedId() {
        return documentBeingApprovedId;
    }

    public String getDocumentBeingApprovedName() {
        return documentBeingApprovedName;
    }

    public String getState() {
        return state;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public String getComments() {
        return comments;
    }

    public String getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setDocumentBeingApprovedId(String documentBeingApprovedId) {
        this.documentBeingApprovedId = documentBeingApprovedId;
    }

    public void setDocumentBeingApprovedName(String documentBeingApprovedName) {
        this.documentBeingApprovedName = documentBeingApprovedName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
