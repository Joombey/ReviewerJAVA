package com.example.reviewerjava.data.model;


public class User {
    private transient int id;
    private transient String name;
    private transient String city;
    private transient String avatar;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
