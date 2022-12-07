package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;

import java.util.List;

public class ReviewBlockViewModel extends ViewModel {
    public LiveData<List<ReportAndReview>> getReportList(){
        return RepositoryController.getReportList();
    }
    public void ban(ReviewEntity review){
        RepositoryController.ban(review);
    }
    public void deny(ReportEntity report){
        RepositoryController.deny(report);
    }

    public void updateReportList(){
        RepositoryController.updateReportList();
    }
}