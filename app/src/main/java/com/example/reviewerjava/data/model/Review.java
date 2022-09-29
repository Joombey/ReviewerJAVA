package com.example.reviewerjava.data.model;

public class Review {
    public String title;
    public String text;
    public String creationTime;
    public String picture;
    private transient Author author;
    private transient Item item;

    public Review(){};

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

    public void setText(String text) {
        this.text = text;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
