package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;

import java.util.List;

public class ReviewBlockViewModel extends ViewModel {
    public LiveData<List<ReportAndReview>> getReportList(){
        return null;
    }
}