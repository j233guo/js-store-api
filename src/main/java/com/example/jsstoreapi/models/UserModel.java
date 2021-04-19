package com.example.jsstoreapi.models;

import org.springframework.data.annotation.Id;

public class UserModel {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

    public UserModel(String id, String firstName, String lastName, String email, String username, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void verify() throws Exception {
        if (firstName == null) {
            throw new Exception("First name is empty");
        }
        if (lastName == null) {
            throw new Exception("Last name is empty");
        }
        if (email == null || !email.contains("@")) {
            throw new Exception("Email is empty / incorrect format");
        }
        if (username == null) {
            throw new Exception("username is empty");
        }
        if (password == null) {
            throw new Exception("password is empty");
        }
        if (!role.equals("user") || !role.equals("admin")) {
            throw new Exception("role can only be user or admin");
        }
    }
}
