package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Review;

import java.util.List;

public interface ReviewListRepository {
    MutableLiveData<List<Review>> getReviewList();
}
