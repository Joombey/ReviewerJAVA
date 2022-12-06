package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;

import java.util.List;

public interface ReviewListRepository {
    LiveData<List<ReviewEntity>> getReviewList();
    ReviewEntity getReviewById(int id);
    LiveData<List<ReviewEntity>> getReviewsByName(String userName);
    ReviewAndUser getReviewAndUserByReviewId(int reviewId);

    void saveAllReviews(List<ReviewEntity> collect);

    void addReportsWithReviews(List<ReportEntity> reportList, List<ReviewEntity> reviewList);
}
