package com.example.nourishbite.model;

import java.util.Date;

public class Order {
    private int id;
    private Date orderDate;
    private boolean status;
    private float total;
    private User user;
    private Payment payment;

    public Order(int id, Date orderDate, boolean status, float total, User user, Payment payment) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
        this.user = user;
        this.payment = payment;
    }

    public Order(Date orderDate, boolean status, float total, User user, Payment payment) {
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
        this.user = user;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
