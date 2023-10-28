package com.example.nourishbite.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String name;
    private float price;
    private String description;
    private String image;
    private boolean status;
    private Material material;

    public Product(int productId, String name, float price, String description, String image, boolean status, Material material) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.material = material;
    }

    public Product(String name, float price, String description, String image, boolean status, Material material) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.material = material;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
