package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.room.models.ReviewEntity;

import java.util.List;

public interface ReviewListRepository {
    LiveData<List<ReviewEntity>> getReviewList();
    ReviewEntity getReviewById(int id);
    LiveData<List<ReviewEntity>> getReviewsByUserId(int userId);
}
