package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long task_id;
    private String name;
    private String owner;
    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User assignedTo;
    @ManyToOne(optional = false)
    @JoinColumn(name="document_d")
    private Document processedDocument;
    private String state;
    private LocalDate dueDate;
    private LocalDate completionDate;
    private String comments;
    @ManyToOne(optional = false)
    @JoinColumn(name="route_id")
    private Route parentRoute;

    public Task(){

    }

    public Task(String name, String owner, User assignedTo, Document processedDocument, String state, LocalDate dueDate, LocalDate completionDate, String comments, Route parentRoute) {
        this.name = name;
        this.owner = owner;
        this.assignedTo = assignedTo;
        this.processedDocument = processedDocument;
        this.state = state;
        this.dueDate = dueDate;
        this.completionDate = completionDate;
        this.comments = comments;
        this.parentRoute = parentRoute;
    }

    public Long getId() {
        return task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Document getProcessedDocument() {
        return processedDocument;
    }

    public void setProcessedDocument(Document processedDocument) {
        this.processedDocument = processedDocument;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Route getParentRoute() {
        return parentRoute;
    }

    public void setParentRoute(Route parentRoute) {
        this.parentRoute = parentRoute;
    }
}
