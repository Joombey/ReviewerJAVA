package com.example.reviewerjava.data.model;


public class Author {
    private int id;
    private String name;
    private String city;

    public Author(String name, String city) {
        this.name = name;
        this.city = city;
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

    public boolean check(String enterLogin, String enterPassword){
        return (enterLogin == "admin" && enterPassword == "password");
    }
}
