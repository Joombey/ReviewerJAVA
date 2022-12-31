package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;

import java.util.List;

@Dao
public interface ReportDao {
    @Query("SELECT * FROM reports WHERE id = :id")
    ReportEntity getReport(int id);

    @Transaction
    @Query("SELECT * FROM reports")
    LiveData<List<ReportAndReview>> getAllReports();

    @Update
    void updateReport(ReportEntity report);

    @Insert
    void createReport(ReportEntity report);

    @Delete
    void deleteReport(ReportEntity report);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReportList(List<ReportEntity> reportList);
}
