package com.example.reviewerjava.ui.viewmodel;

import com.example.reviewerjava.data.repository.RepositoryController;

public class SignUpViewModel {
    public void signUp(String login, String password) {
        RepositoryController.signUp(login, password);
    }
}
