package com.example.reviewerjava.data.model;


import java.util.List;

public class Shop {
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
