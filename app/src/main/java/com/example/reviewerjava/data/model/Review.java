package com.example.reviewerjava.data.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;

@Entity(tableName = "reviews")
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String text;
    private String creationTime;

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAuthor(){
        return this.author;
    }

    private String author;
    private String picture;
    private String item;

    public String getItem(){
        return this.item;
    }

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
        this.item = new Gson().toJson(item, Item.class);
        this.author = new Gson().toJson(author, Author.class);
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

    public Author getAuthorInstance() {
        return (new Gson()).fromJson(this.author, Author.class);
    }

    public void setAuthor(Author author) {
        this.author = new Gson().toJson(author, Author.class);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Item getItemInstance() {
        return new Gson().fromJson(this.item, Item.class);
    }

    public void setItem(Item item) {
        this.item = new Gson().toJson(item, Item.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
