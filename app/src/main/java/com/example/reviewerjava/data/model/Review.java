package com.example.reviewerjava.data.model;


public class Review {
    private String title;
    private String text;
    private String creationTime;
    private Author author;
    private String picture;
    private Item item;

    public Review(
            String title,
            String text,
            String creationTime,
            Author author,
            String picture,
            Item item
    ){
        this.title = title;
        this.text = text;
        this.creationTime = creationTime;
        this.picture = picture;
        this.item = item;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
