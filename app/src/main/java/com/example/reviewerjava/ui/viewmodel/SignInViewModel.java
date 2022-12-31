package com.example.reviewerjava.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.example.reviewerjava.di.ServiceLocator;

public class SignInViewModel extends ViewModel {
    public LiveData<Boolean> signIn(String login, String password){
        return ServiceLocator.getInstance().getReviewerApi().signIn(login, password);
//        return RepositoryController.signIn(login, password);
    }
}
