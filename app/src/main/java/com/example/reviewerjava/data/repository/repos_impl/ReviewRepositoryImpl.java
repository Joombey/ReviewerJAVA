package com.example.reviewerjava.data.repository.repos_impl;


import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.repository.repos.ReviewRepository;
import com.example.reviewerjava.data.room.ReviewerRoomDb;
import com.example.reviewerjava.data.room.daos.ReviewDao;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;

import java.util.List;

public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewDao dao;

    public ReviewRepositoryImpl(ReviewDao dao){
        this.dao = dao;
    }

    @Override
    public LiveData<List<ReviewEntity>> getReviewList() {
        return dao.getAllReviews();
    }

    @Override
    public LiveData<List<ReviewAndUser>> getReviewAndUserList() {
        return dao.getReviewAndUserList();
    }

    @Override
    public LiveData<List<ReviewAndUser>> getReviewAndUserListByAuthor(String userName) {
        return dao.getReviewAndUserListByAuthor(userName);
    }

    @Override
    public ReviewEntity getReviewById(int id) {
        return dao.getReviewById(id);
    }

    @Override
    public LiveData<List<ReviewEntity>> getReviewsByAuthor(String userName) {
        return dao.getReviewsByAuthor(userName);
    }

    @Override
    public ReviewAndUser getReviewAndUserByReviewId(int reviewId) {
        return dao.getReviewAndUserByReviewId(reviewId);
    }

    @Override
    public void ban(ReviewEntity review) {
        dao.deleteReview(review);
    }

    @Override
    public void addReviewList(List<ReviewEntity> reviewList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertReviewList(reviewList);
        });
    }

    @Override
    public void addReview(ReviewEntity review) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertReview(review);
        });
    }

    @Override
    public void addReportsWithReviews(List<ReportEntity> reportList, List<ReviewEntity> reviewList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertReviewsWithReports(reportList, reviewList);
        });
    }
}
