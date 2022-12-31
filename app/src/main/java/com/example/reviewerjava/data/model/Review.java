package com.example.reviewerjava.data.model;

import java.util.List;

public class Review {
    public transient String reviewTitle;
    public transient String creationTime;
    private transient List<Paragraph> paragraphList;
    private transient User user;
    private transient Item item;

    public Review(){}

    public Review(
            String reviewTitle,
            List<Paragraph> paragraphList,
            String creationTime,
            User user,
            Item item
    ){
        this.reviewTitle = reviewTitle;
        this.paragraphList = paragraphList;
        this.creationTime = creationTime;
        this.item = item;
        this.user = user;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public List<Paragraph> getParagraphList() {
        return paragraphList;
    }

    public void setParagraphList(List<Paragraph> paragraphList) {
        this.paragraphList = paragraphList;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getAuthor() {
        return user.getName();
    }

    public void setAuthor(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
