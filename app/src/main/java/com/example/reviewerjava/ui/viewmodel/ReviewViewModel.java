package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewEntity;

public class ReviewViewModel extends ViewModel {
    public ReviewEntity getReviewById(int id){
        return RepositoryController.getReviewListRepository().getReviewById(id);
    }
}
