package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

@Dao
public interface ReviewDAO {
    @Query("SELECT * FROM reviews")
    LiveData<List<ReviewEntity>> getAllReviews();

    @Insert
    void insertReview(ReviewEntity review);

    @Query("SELECT * FROM reviews WHERE id == :id")
    ReviewEntity getReviewById(int id);

    @Transaction()
    @Query("SELECT * FROM users WHERE role == :role")
    UserAndPermission getUser(String role);

    @Insert
    void insertUser(UserEntity user);
}
