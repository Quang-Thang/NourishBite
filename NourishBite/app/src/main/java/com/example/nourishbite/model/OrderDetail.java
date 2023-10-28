package com.example.nourishbite.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private int id;
    private int amount;
    private float price;
    private Order order;
    private Product product;

    public OrderDetail(int id, int amount, float price, Order order, Product product) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
