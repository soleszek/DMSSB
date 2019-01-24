package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Task extends DMSObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String owner;
    private String assignedTo;
    private String documentBeingApprovedId;
    private String documentBeingApprovedName;
    private LocalDate dueDate;
    private LocalDate completionDate;
    private String parentId;

    public Task(){

    }

    public Task(String name, String state, LocalDate creationDate, String comments, String owner, String assignedTo, String documentBeingApprovedId, String documentBeingApprovedName, LocalDate dueDate, LocalDate completionDate, String parentId) {
        super(name, state, creationDate, comments);
        this.owner = owner;
        this.assignedTo = assignedTo;
        this.documentBeingApprovedId = documentBeingApprovedId;
        this.documentBeingApprovedName = documentBeingApprovedName;
        this.dueDate = dueDate;
        this.completionDate = completionDate;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public String getParentId() {
        return parentId;
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
