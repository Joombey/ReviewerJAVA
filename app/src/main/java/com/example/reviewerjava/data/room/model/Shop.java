package com.example.reviewerjava.data.room.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Shop {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private List<String> cities;

    public Shop(String title, List<String> cities){
        this.title = title;
        this.cities = cities;
    }

    public List<String> getCities() {
        return cities;
    }

    public String getTitle() {
        return title;
    }

    public void setCities(List<String> mCities) {
        this.cities = mCities;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
