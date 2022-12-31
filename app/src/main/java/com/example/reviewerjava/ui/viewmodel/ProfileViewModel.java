package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.di.ServiceLocator;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    public LiveData<List<ReviewEntity>> getUserReviews(String userName){
        return ServiceLocator.getInstance().getReviewRepository().getReviewsByAuthor(userName);
//        return RepositoryController.getReviewsName(userName);
    }

    public UserEntity getUserByName(String userName){
        return ServiceLocator.getInstance().getUserRepository().getUserByName(userName);
//        return RepositoryController.getUserByName(userName);
    }

    public String getCurrentUserName() {
        return CurrentUser.getInstance().getUser().getName();
//        return RepositoryController.getCurrentUserName();
    }


    public void logOut(){
        CurrentUser.getInstance().setUserAndPermission(
                CurrentUser.UNAUTHORIZED_USER
        );
        CurrentUser.getInstance().userId = null;
        CurrentUser.getInstance().access_token = null;
//        RepositoryController.logOut();
    }
}