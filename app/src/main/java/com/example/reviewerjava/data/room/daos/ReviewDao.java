package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;

import java.util.List;

@Dao
public interface ReviewDao {
    @Query("SELECT * FROM reviews")
    LiveData<List<ReviewEntity>> getAllReviews();

    @Query("SELECT * FROM reviews WHERE id == :id")
    ReviewEntity getReviewById(int id);

    @Query("SELECT * FROM reviews WHERE author = :userName")
    LiveData<List<ReviewEntity>> getReviewsByAuthor(String userName);

    @Transaction
    @Query("SELECT * FROM reviews WHERE author == :userName")
    LiveData<List<ReviewAndUser>> getReviewAndUserListByAuthor(String userName);

    @Transaction
    @Query("SELECT * FROM reviews")
    LiveData<List<ReviewAndUser>> getReviewAndUserList();

    @Transaction
    @Query("SELECT * FROM reviews WHERE id =:reviewId")
    ReviewAndUser getReviewAndUserByReviewId(int reviewId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReview(ReviewEntity review);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReviewList(List<ReviewEntity> reviewList);

    @Transaction
    @Insert
    default void insertReviewsWithReports(List<ReportEntity> reportList, List<ReviewEntity> reviewList){
        insertReviewList(reviewList);
        insertReportList(reportList);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReportList(List<ReportEntity> reportList);

    @Delete
    void deleteReview(ReviewEntity review);
}
