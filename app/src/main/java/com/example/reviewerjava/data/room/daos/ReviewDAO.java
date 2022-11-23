package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
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

    @Transaction
    @Query("SELECT * FROM users WHERE name == :name")
    UserAndPermission getUser(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Query("SELECT * FROM reviews WHERE author = :userName")
    LiveData<List<ReviewEntity>> getReviewsByName(String userName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUserState(UserEntity user);

    @Query("SELECT * FROM users WHERE name = :userName")
    UserEntity getUserByName(String userName);

    @Query("SELECT * FROM permissions WHERE role = :role")
    PermissionEntity getPermission(String role);

    @Transaction
    @Query("SELECT * FROM reviews WHERE id =:reviewId")
    ReviewAndUser getReviewAndUserByReviewId(int reviewId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPermission(PermissionEntity permission);

    @Transaction
    @Query("SELECT * FROM reports")
    LiveData<List<ReportAndReview>> getAllReports();

    @Delete
    void deleteReview(ReviewEntity review);

    @Query("DELETE FROM reviews where id=:id")
    void deleteReview(int id);

    @Delete
    void deleteReport(ReportEntity report);

    @Query("SELECT * from reports WHERE id = :id")
    ReportEntity getReport(int id);

    @Insert
    void addReport(ReportEntity reportEntity);

    @Update
    void updateReport(ReportEntity report);

    @Query("SELECT * FROM users WHERE name !=:name")
    LiveData<List<UserEntity>> getUsers(String name);

    @Delete
    void deleteUser(UserEntity user);

    @Insert
    void addNewUser(UserEntity newUser);
}
