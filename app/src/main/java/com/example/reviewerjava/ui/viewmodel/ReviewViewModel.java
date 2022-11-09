package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.User;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;

public class ReviewViewModel extends ViewModel {
    public ReviewEntity getReviewById(int id){
        return RepositoryController.getReviewById(id);
    }
    public UserEntity getUserById(int id){
        return RepositoryController.getUserById(id);
    }
}
