package com.example.reviewerjava.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.di.ServiceLocator;

public class SignUpViewModel extends ViewModel {
    public LiveData<Boolean> signUp(String login, String password, String city, String avatar) {
//        UserRequest userRequest = new UserRequest(new UserId(login, password), city, avatar);
        return ServiceLocator.getInstance().getReviewerApi().signUp(login, password, city, avatar);
//        return RepositoryController.signUp(login, password, city, avatar);
    }
}
