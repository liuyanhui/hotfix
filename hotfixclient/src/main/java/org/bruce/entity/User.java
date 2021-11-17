package org.bruce.entity;

public class User {
    private String firstName;

    private String lastName;

//    private String id;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return lastName + ".." + firstName;
    }
}
