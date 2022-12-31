package com.example.reviewerjava.data.repository.repoimpl;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.repository.repos.ReviewRepository;
import com.example.reviewerjava.data.room.ReviewerRoomDb;
import com.example.reviewerjava.data.room.daos.ReviewDao;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;

import java.util.List;

public class ReviewRepositoryImpl implements ReviewRepository {

    private ReviewDao dao;

    public ReviewRepositoryImpl(ReviewDao dao){
        this.dao = dao;
    }

    @Override
    public LiveData<List<ReviewEntity>> getReviewList() {
        return dao.getAllReviews();
    }

    @Override
    public ReviewEntity getReviewById(int id) {
        return dao.getReviewById(id);
    }

    @Override
    public LiveData<List<ReviewEntity>> getReviewsByAuthor(String userName) {
        return dao.getReviewsByName(userName);
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
    public void saveAllReviews(List<ReviewEntity> reviewList) {
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
