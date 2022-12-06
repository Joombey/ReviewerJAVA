package com.example.reviewerjava.data.retrofit.request;

import com.example.reviewerjava.data.retrofit.request.pks.ReviewId;
import com.example.reviewerjava.data.room.models.ReviewEntity;

public class ReviewDto {
    private ReviewId id;
    private String item;
    private String paragraphs;

    public ReviewDto(ReviewEntity review) {
        this.id = new ReviewId(review.id, review.author);
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

    public static ReviewEntity convertToEntity(ReviewDto review){
        return new ReviewEntity(
                review.getId().getId(),
                review.getId().getAuthor(),
                review.getItem(),
                review.getParagraphs()
        );
    }
}

