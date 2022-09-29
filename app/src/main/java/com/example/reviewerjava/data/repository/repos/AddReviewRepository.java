package com.example.reviewerjava.data.repository.repos;

import com.example.reviewerjava.data.model.Review;

public interface AddReviewRepository {
    <T extends Review> void addReview(T review);
}
