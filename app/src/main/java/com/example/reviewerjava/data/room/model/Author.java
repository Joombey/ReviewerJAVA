package com.example.reviewerjava.data.room.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "authors")
public class Author {
    @PrimaryKey(autoGenerate = true)
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
