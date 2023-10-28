package com.example.nourishbite.model;

public class Material {
    private int materialId;
    private String name;
    private String description;

    public Material(int materialId, String name, String description) {
        this.materialId = materialId;
        this.name = name;
        this.description = description;
    }

    public int getmaterialId() {
        return materialId;
    }

    public void setmaterialId(int id) {
        this.materialId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
