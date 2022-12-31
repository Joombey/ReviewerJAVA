package com.example.reviewerjava.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.di.ServiceLocator;

import java.util.List;

public class ReviewBlockViewModel extends ViewModel {
    public LiveData<List<ReportAndReview>> getReportList(){
        return ServiceLocator.getInstance().getReportRepository().getReports();
//        return RepositoryController.getReportList();
    }
    public void ban(ReviewEntity review){
        ServiceLocator.getInstance().getReviewRepository().ban(review);
//        RepositoryController.ban(review);
    }
    public void deny(ReportEntity report){
        ServiceLocator.getInstance().getReportRepository().deny(report);
//        RepositoryController.deny(report);
    }

    public void updateReportList(){
//        ServiceLocator.getInstance().getReportRepository().updateReportList();
        ServiceLocator.getInstance().getReviewerApi().updateReportList();
//        RepositoryController.updateReportList();
    }
}