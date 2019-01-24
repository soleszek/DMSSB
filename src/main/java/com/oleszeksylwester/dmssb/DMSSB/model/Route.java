package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Route extends DMSObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String owner;
    private LocalDate finishDate;
    private LocalDate deadline;
    private String documentBeingApprovedId;
    private String documentBeingApprovedName;
    private LocalDate checkingDueDate;
    private String responsibleForChecking;
    private String responsibleForApproving;

    public Route() {
    }

    public Route(String name, String state, LocalDate creationDate, String comments, String owner, LocalDate finishDate, LocalDate deadline, String documentBeingApprovedId, String documentBeingApprovedName, LocalDate checkingDueDate, String responsibleForChecking, String responsibleForApproving) {
        super(name, state, creationDate, comments);
        this.owner = owner;
        this.finishDate = finishDate;
        this.deadline = deadline;
        this.documentBeingApprovedId = documentBeingApprovedId;
        this.documentBeingApprovedName = documentBeingApprovedName;
        this.checkingDueDate = checkingDueDate;
        this.responsibleForChecking = responsibleForChecking;
        this.responsibleForApproving = responsibleForApproving;
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getDocumentBeingApprovedId() {
        return documentBeingApprovedId;
    }

    public String getDocumentBeingApprovedName() {
        return documentBeingApprovedName;
    }

    public LocalDate getCheckingDueDate() {
        return checkingDueDate;
    }

    public String getResponsibleForChecking() {
        return responsibleForChecking;
    }

    public String getResponsibleForApproving() {
        return responsibleForApproving;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDocumentBeingApprovedId(String documentBeingApprovedId) {
        this.documentBeingApprovedId = documentBeingApprovedId;
    }

    public void setDocumentBeingApprovedName(String documentBeingApprovedName) {
        this.documentBeingApprovedName = documentBeingApprovedName;
    }

    public void setCheckingDueDate(LocalDate checkingDueDate) {
        this.checkingDueDate = checkingDueDate;
    }

    public void setResponsibleForChecking(String responsibleForChecking) {
        this.responsibleForChecking = responsibleForChecking;
    }

    public void setResponsibleForApproving(String responsibleForApproving) {
        this.responsibleForApproving = responsibleForApproving;
    }
}
