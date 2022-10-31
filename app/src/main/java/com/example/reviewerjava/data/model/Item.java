package com.example.reviewerjava.data.model;


public class Item {

    private String shop;
    private String itemName;
    private String itemImage;
    private String description;
    private String productId;

    public Item(String shop, String itemName, String itemImage, String description, String productId) {
        this.shop = shop;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.description = description;
        this.productId = productId;
    }

    public Item(){};

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemShops() {
        return shop;
    }

    public void setItemShop(String shop) {
        this.shop = shop;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}