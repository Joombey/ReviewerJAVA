package com.example.reviewerjava.presentation.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.di.ServiceLocator;

public class SignInViewModel extends ViewModel {
    public LiveData<Boolean> signIn(String login, String password){
        return ServiceLocator.getInstance().getReviewerApi().signIn(login, password);
//        return RepositoryController.signIn(login, password);
    }
}
