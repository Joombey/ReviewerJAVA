package com.example.reviewerjava.presentation.viewmodel;

import android.app.Service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
import com.example.reviewerjava.di.ServiceLocator;

import java.util.List;

public class ReviewListViewModel extends ViewModel {
    public LiveData<List<ReviewAndUser>> getReviews(){
        return ServiceLocator.getInstance().getReviewRepository().getReviewAndUserList();
    }

    public void updateReviewList(){
        ServiceLocator.getInstance().getReviewerApi().fetchReviewAndUserList();
    }
}
