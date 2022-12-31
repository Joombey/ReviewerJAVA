package com.example.reviewerjava.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
import com.example.reviewerjava.di.ServiceLocator;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    public LiveData<List<ReviewAndUser>> getUserReviews(String userName){
        return ServiceLocator.getInstance().getReviewRepository().getReviewAndUserListByAuthor(userName);
    }

    public UserEntity getUserByName(String userName){
        return ServiceLocator.getInstance().getUserRepository().getUserByName(userName);
    }

    public String getCurrentUserName() {
        return CurrentUser.getInstance().getUser().getName();
    }


    public void logOut(){
        CurrentUser.getInstance().setUserAndPermission(
                CurrentUser.UNAUTHORIZED_USER
        );
        CurrentUser.getInstance().userId = null;
        CurrentUser.getInstance().access_token = null;
    }
}