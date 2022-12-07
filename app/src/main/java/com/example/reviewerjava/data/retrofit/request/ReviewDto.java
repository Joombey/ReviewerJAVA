package com.example.reviewerjava.data.retrofit.request;

import com.example.reviewerjava.data.retrofit.request.pks.ReviewId;
import com.example.reviewerjava.data.room.models.ReviewEntity;

public class ReviewDto {
    private ReviewId id;
    private String reviewTitle;
    private String creationTime;
    private String item;
    private String paragraphs;

    public ReviewDto(ReviewEntity review) {
        this.id = new ReviewId(review.id, review.author);
        this.reviewTitle = review.reviewTitle;
        this.creationTime = review.getCreationTime();
        this.item = review.item;
        this.paragraphs = review.paragraphs;
    }

    public ReviewId getId() {
        return id;
    }

    public void setId(ReviewId id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public static ReviewEntity convertToEntity(ReviewDto review){
        return new ReviewEntity(review);
    }
}

