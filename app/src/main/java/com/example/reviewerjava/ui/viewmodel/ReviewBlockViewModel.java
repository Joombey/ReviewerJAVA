package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.room.models.ReportEntity;

public class ReviewBlockViewModel extends ViewModel {
    LiveData<ReportEntity> getReportList(){
        return null;
    }
}