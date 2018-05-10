package com.koch.java.kochjava.base.model;

import io.objectbox.annotation.Entity;

@Entity
public class User extends BaseModel {
    private String firstName;
    private String lastName;

    public User() {
        this("", "");
    }

    public User(String firstName, String lastName) {
        //allow only single user to exist
        this.id = 1L;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String value) {
        firstName = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String value) {
        this.lastName = value;
    }
}
