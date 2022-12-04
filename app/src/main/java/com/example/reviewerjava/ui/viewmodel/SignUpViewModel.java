package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;

public class SignUpViewModel extends ViewModel {
    public boolean signUp(String login, String password, String city, String avatar) {
        return RepositoryController.signUp(login, password, city, avatar);
    }
}
