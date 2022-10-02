package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.reviewerjava.data.room.models.ReviewRoom;

import java.util.List;

@Dao
public interface ReviewDAO {
    @Query("SELECT * FROM reviews")
    LiveData<List<ReviewRoom>> getAllReviews();

    @Insert
    void insertReview(ReviewRoom review);
}
