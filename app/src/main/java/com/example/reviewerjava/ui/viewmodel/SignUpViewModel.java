package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.retrofit.request.UserRequest;
import com.example.reviewerjava.data.retrofit.request.pks.UserId;
import com.example.reviewerjava.di.ServiceLocator;

public class SignUpViewModel extends ViewModel {
    public boolean signUp(String login, String password, String city, String avatar) {
//        UserRequest userRequest = new UserRequest(new UserId(login, password), city, avatar);
        return ServiceLocator.getInstance().getReviewerApi().signUp(login, password, city, avatar);
//        return RepositoryController.signUp(login, password, city, avatar);
    }
}
