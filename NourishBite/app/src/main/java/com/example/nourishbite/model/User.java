package com.example.nourishbite.model;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String email;
    private String userName;
    private String password;
    private String description;
    private String address;

    public User(String email, String userName, String password, String description, String address) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.description = description;
        this.address = address;
    }

    public User(int userId, String email, String userName, String password, String description) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.description = description;
    }

    public User(String email, String userName, String password, String address) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }

    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
