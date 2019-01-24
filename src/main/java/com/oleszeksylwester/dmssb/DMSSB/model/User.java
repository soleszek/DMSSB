package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends DMSObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    /*private Set<Role> roles;*/

    public User() {
    }

    public User(String name, String state, LocalDate creationDate, String comments, String firstName, String lastName, String username, String password) {
        super(name, state, creationDate, comments);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
