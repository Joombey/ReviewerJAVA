package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    public LiveData<UserAndPermission> getUserAndPermission(){
        return RepositoryController.getCurrentUserData();
    }

    public LiveData<List<ReviewEntity>> getUserReviews(String userName){
        return RepositoryController.getReviewsName(userName);
    }

    public void updateUser(UserEntity user){
        RepositoryController.updateUser(user);
    }

    public UserEntity getUserByName(String userName){
        return RepositoryController.getUserByName(userName);
    }

    public String getCurrentUserName() {
        return RepositoryController.getCurrentUserName();
    }
    public void logOut(){
        RepositoryController.logOut();
    }
}