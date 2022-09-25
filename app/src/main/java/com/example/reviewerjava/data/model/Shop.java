package com.example.reviewerjava.data.model;

import java.util.List;

public class Shop {
    private String title;
    private List<String> cities;

    public Shop(String title, List<String> cities){
        this.cities = cities;
        this.title = title;
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
}
