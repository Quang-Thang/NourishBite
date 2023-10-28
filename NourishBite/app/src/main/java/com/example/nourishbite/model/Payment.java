package com.example.nourishbite.model;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private int id;
    private Date paymentDate;
    private String paymentMethod;
    private int amount;
    private User user;

    public Payment(Date paymentDate, String paymentMethod, int amount, User user) {
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.user = user;
    }

    public Payment(int id, Date paymentDate, String paymentMethod, int amount, User user) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
