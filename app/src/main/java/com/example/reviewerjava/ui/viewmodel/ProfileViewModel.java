package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    public LiveData<UserAndPermission> getUserAndPermission(){
        return RepositoryController.getCurrentUserData();
    }

    public LiveData<List<ReviewEntity>> getUserReviews(int userId){
        return RepositoryController.getReviewsByUserId(userId);
    }

    public void updateUser(UserEntity user){
        RepositoryController.updateUser(user);
    }

    public UserEntity getUserById(int id){
        return RepositoryController.getUserById(id);
    }

    public int getCurrentUserId() {
        return RepositoryController.getCurrentUserId();
    }
}