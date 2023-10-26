package com.example.nourishbite.model;

import java.util.Date;

public class Notification {
    private int id;
    private String content;
    private Date sentTime;
    private User user;

    public Notification(int id, String content, Date sentTime, User user) {
        this.id = id;
        this.content = content;
        this.sentTime = sentTime;
        this.user = user;
    }

    public Notification(String content, Date sentTime, User user) {
        this.content = content;
        this.sentTime = sentTime;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
