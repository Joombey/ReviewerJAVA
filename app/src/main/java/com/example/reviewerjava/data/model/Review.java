package com.example.reviewerjava.data.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Review {
    private Map<String, String> paragraph;
    private LocalDateTime creationTime;
    private Author author;
    private String picture;
    private Item item;

    public Review(
        Map<String, String> paragraph,
        LocalDateTime creationTime,
        Author author,
        String picture,
        Item item
    ){
        this.paragraph = paragraph;
        this.creationTime = creationTime;
        this.picture = picture;
        this.item = item;
        this.author = author;
    }

    public Map<String, String> getParagraph() {
        return paragraph;
    }

    public void setParagraph(Map<String, String> paragraph) {
        this.paragraph = paragraph;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
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
