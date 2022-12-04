package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;

import java.util.List;

@Dao
public interface ReportDao {
    @Delete
    void deleteReport(ReportEntity report);

    @Query("SELECT * FROM reports WHERE id = :id")
    ReportEntity getReport(int id);

    @Insert
    void createReport(ReportEntity report);

    @Update
    void updateReport(ReportEntity report);

    @Transaction
    @Query("SELECT * FROM reports")
    LiveData<List<ReportAndReview>> getAllReports();
}
