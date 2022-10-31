package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewEntity;

import java.util.List;

public class ReviewListViewModel extends ViewModel {
    public LiveData<List<ReviewEntity>> getReviews(){
        return RepositoryController.getReviewListRepository().getReviewList();
    }
}
