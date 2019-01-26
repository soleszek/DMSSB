package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("task")
public class Task extends DMSObject{

    private String owner;
    private String assignedTo;
    private String documentBeingApprovedId;
    private String documentBeingApprovedName;
    private LocalDate dueDate;
    private LocalDate completionDate;
    private String parentId;

    public Task(){

    }

    public Task(String name, String state, LocalDate creationDate, LocalDate lastModification, String comments, String owner, String assignedTo, String documentBeingApprovedId, String documentBeingApprovedName, LocalDate dueDate, LocalDate completionDate, String parentId) {
        super(name, state, creationDate, lastModification, comments);
        this.owner = owner;
        this.assignedTo = assignedTo;
        this.documentBeingApprovedId = documentBeingApprovedId;
        this.documentBeingApprovedName = documentBeingApprovedName;
        this.dueDate = dueDate;
        this.completionDate = completionDate;
        this.parentId = parentId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDocumentBeingApprovedId() {
        return documentBeingApprovedId;
    }

    public void setDocumentBeingApprovedId(String documentBeingApprovedId) {
        this.documentBeingApprovedId = documentBeingApprovedId;
    }

    public String getDocumentBeingApprovedName() {
        return documentBeingApprovedName;
    }

    public void setDocumentBeingApprovedName(String documentBeingApprovedName) {
        this.documentBeingApprovedName = documentBeingApprovedName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
