package com.example.reviewerjava.data.repository.repos;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.room.models.ReviewEntity;

public interface AddReviewRepository {
    void addReview(ReviewEntity review);
}
