package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.room.models.ReviewEntity;

import java.util.List;

public interface ReviewListRepository {
    <T extends Review> LiveData<List<T>> getReviewList();
    ReviewEntity getReviewById(int id);
}
