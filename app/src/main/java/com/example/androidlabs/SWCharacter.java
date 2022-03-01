package com.example.androidlabs;

public class SWCharacter {
    private String name;
    private String age;
    private String mass;

    public SWCharacter(String name, String age, String mass) {
        this.name = name;
        this.age = age;
        this.mass = mass;
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

    @Override
    public String toString() {
        return "SWCharacter{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", mass='" + mass + '\'' +
                '}';
    }
}
