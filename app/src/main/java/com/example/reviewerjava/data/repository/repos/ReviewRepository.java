package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;

import java.util.List;

public interface ReviewRepository {
    LiveData<List<ReviewEntity>> getReviewsByAuthor(String userName);
    LiveData<List<ReviewEntity>> getReviewList();

    LiveData<List<ReviewAndUser>> getReviewAndUserList();
    LiveData<List<ReviewAndUser>> getReviewAndUserListByAuthor(String userName);

    ReviewEntity getReviewById(int id);
    ReviewAndUser getReviewAndUserByReviewId(int reviewId);

    void ban(ReviewEntity review);
    void addReviewList(List<ReviewEntity> reviewList);
    void addReview(ReviewEntity review);
    void addReportsWithReviews(List<ReportEntity> reportList, List<ReviewEntity> reviewList);
}
