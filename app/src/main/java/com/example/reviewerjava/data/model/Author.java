package com.example.reviewerjava.data.model;


public class Author {
    private int id;
    private String name;
    private String city;
    private String avatar;

    public Author(String name, String city, String avatar) {
        this.name = name;
        this.city = city;
        this.avatar = avatar;
    }

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
