package com.example.reviewerjava.data.retrofit.response;

import com.example.reviewerjava.data.retrofit.response.embadable.ReviewId;

public class ReviewerReviewResponse {
    public ReviewId id;
    public String item;
    public String paragraphs;

    public ReviewerReviewResponse(ReviewId id, String item, String paragraphs) {
        this.id = id;
        this.item = item;
        this.paragraphs = paragraphs;
    }
}
