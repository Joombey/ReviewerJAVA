package com.example.reviewerjava.data.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    private String itemName;
    private List<String> itemShops;

    public Item(String itemName, List<String> itemShops) {
        this.itemName = itemName;
        this.itemShops = itemShops;
    }
    public Item(){};

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<String> getItemShops() {
        return itemShops;
    }

    public void setItemShops(List<String> itemShops) {
        this.itemShops = itemShops;
    }
}