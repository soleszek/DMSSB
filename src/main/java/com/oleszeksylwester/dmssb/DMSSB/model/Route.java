package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("route")
public class Route extends DMSObject {

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

    public Route(String name, String state, LocalDate creationDate, LocalDate lastModification, String comments, String owner, LocalDate finishDate, LocalDate deadline, String documentBeingApprovedId, String documentBeingApprovedName, LocalDate checkingDueDate, String responsibleForChecking, String responsibleForApproving) {
        super(name, state, creationDate, lastModification, comments);
        this.owner = owner;
        this.finishDate = finishDate;
        this.deadline = deadline;
        this.documentBeingApprovedId = documentBeingApprovedId;
        this.documentBeingApprovedName = documentBeingApprovedName;
        this.checkingDueDate = checkingDueDate;
        this.responsibleForChecking = responsibleForChecking;
        this.responsibleForApproving = responsibleForApproving;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
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

    public LocalDate getCheckingDueDate() {
        return checkingDueDate;
    }

    public void setCheckingDueDate(LocalDate checkingDueDate) {
        this.checkingDueDate = checkingDueDate;
    }

    public String getResponsibleForChecking() {
        return responsibleForChecking;
    }

    public void setResponsibleForChecking(String responsibleForChecking) {
        this.responsibleForChecking = responsibleForChecking;
    }

    public String getResponsibleForApproving() {
        return responsibleForApproving;
    }

    public void setResponsibleForApproving(String responsibleForApproving) {
        this.responsibleForApproving = responsibleForApproving;
    }
}
