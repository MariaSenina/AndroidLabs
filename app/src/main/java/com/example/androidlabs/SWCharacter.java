package com.example.androidlabs;

public class SWCharacter {
    private String name;
    private String height;
    private String mass;
    private long id;

    public SWCharacter(String name, String height, String mass, int id) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.id = (long) id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SWCharacter{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                '}';
    }
}
