package com.example.reviewerjava.data.model;

import java.util.List;

public class Item {
    private String itemName;
    private List<Shop> itemShops;

    public Item(String itemName, List<Shop> itemShops) {
        this.itemName = itemName;
        this.itemShops = itemShops;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Shop> getItemShops() {
        return itemShops;
    }

    public void setItemShops(List<Shop> itemShops) {
        this.itemShops = itemShops;
    }
}