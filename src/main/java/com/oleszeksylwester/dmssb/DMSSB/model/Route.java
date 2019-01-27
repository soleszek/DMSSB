package com.oleszeksylwester.dmssb.DMSSB.model;

import com.oleszeksylwester.dmssb.DMSSB.factory.TaskFactory;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Route extends TaskFactory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String owner;
    private String state;
    private LocalDate creationDate;
    private LocalDate finishDate;
    private LocalDate deadline;
    private String documentBeingApprovedId;
    private String documentBeingApprovedName;
    private LocalDate checkingDueDate;
    private String responsibleForChecking;
    private String responsibleForApproving;
    private String comments;

    public Route() {
    }

    public static class Builder {
        private String name;
        private String owner;
        private String state;
        private LocalDate creationDate;
        private LocalDate finishDate;
        private LocalDate deadline;
        private String documentBeingApprovedId;
        private String documentBeingApprovedName;
        private LocalDate checkingDueDate;
        private String responsibleForChecking;
        private String responsibleForApproving;
        private String comments;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder owner(String owner){
            this.owner = owner;
            return this;
        }

        public Builder state(String state){
            this.state = state;
            return this;
        }

        public Builder creationDate(LocalDate creationDate){
            this.creationDate = creationDate;
            return this;
        }

        public Builder finishDate(LocalDate finishDate){
            this.finishDate = finishDate;
            return this;
        }

        public Builder deadline(LocalDate deadline){
            this.deadline = deadline;
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

        public Builder checkingDueDate(LocalDate checkingDueDate){
            this.checkingDueDate = checkingDueDate;
            return this;
        }

        public Builder responsibleForChecking (String responsibleForChecking){
            this.responsibleForChecking = responsibleForChecking;
            return this;
        }

        public Builder responsibleForApproving(String responsibleForApproving){
            this.responsibleForApproving = responsibleForApproving;
            return this;
        }

        public Builder comments(String comments){
            this.comments = comments;
            return this;
        }

        public Route build(){
            return new Route(this);
        }
    }

    private Route(Builder builder){
        this.name = builder.name;
        this.owner = builder.owner;
        this.state = builder.state;
        this.creationDate = builder.creationDate;
        this.finishDate = builder.finishDate;
        this.deadline = builder.deadline;
        this.documentBeingApprovedId = builder.documentBeingApprovedId;
        this.documentBeingApprovedName = builder.documentBeingApprovedName;
        this.checkingDueDate = builder.checkingDueDate;
        this.responsibleForChecking = builder.responsibleForChecking;
        this.responsibleForApproving = builder.responsibleForApproving;
        this.comments = builder.comments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner(){
        return owner;
    }

    public String getState() {
        return state;
    }

    public LocalDate getCreationDate() {
        return creationDate;
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

    public LocalDate getCheckingDueDate(){
        return checkingDueDate;
    }

    public String getResponsibleForChecking() {
        return responsibleForChecking;
    }

    public String getResponsibleForApproving() {
        return responsibleForApproving;
    }

    public String getComments() {
        return comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public void setComments(String comments) {
        this.comments = comments;
    }
}
