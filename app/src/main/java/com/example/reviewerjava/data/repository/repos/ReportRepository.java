package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;

import java.util.List;

public interface ReportRepository {
    LiveData<List<ReportAndReview>> getReports();
    void ban(ReviewEntity review);
    void deny(ReportEntity report);
    void report(int id);
}
