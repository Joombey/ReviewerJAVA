package com.example.reviewerjava.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Review;

import java.util.List;

public interface Repository {
    MutableLiveData<List<Review>> getReviewList();
    void createNewReview(Review review);
}
