package com.example.reviewerjava.data.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    private String itemName;
    private List<String> itemShops;
    private String itemImage;
    private String desctiption;

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

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }
}