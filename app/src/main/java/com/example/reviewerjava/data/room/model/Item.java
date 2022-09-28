package com.example.reviewerjava.data.room.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "items")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemName;
    private List<Shop> itemShops;
    public Item(String itemName, List<Shop> itemShops) {
        this.itemName = itemName;
        this.itemShops = itemShops;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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