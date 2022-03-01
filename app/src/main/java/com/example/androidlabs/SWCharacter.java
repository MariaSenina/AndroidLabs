package com.example.androidlabs;

public class SWCharacter {
    private String name;
    private String age;
    private String mass;
    private long id;

    public SWCharacter(String name, String age, String mass, int id) {
        this.name = name;
        this.age = age;
        this.mass = mass;
        this.id = (long) id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
                ", age='" + age + '\'' +
                ", mass='" + mass + '\'' +
                '}';
    }
}
