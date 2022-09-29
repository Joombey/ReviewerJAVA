package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.model.Review;

import java.util.List;

public interface ReviewListRepository {
    <T extends Review> LiveData<List<T>> getReviewList();
}
