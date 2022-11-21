package com.example.reviewerjava.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

public class SignInViewModel extends ViewModel {
    public boolean signIn(String login, String password){
        return RepositoryController.signIn(login, password);
    }

    public LiveData<UserAndPermission> getUser(){
        return CurrentUser.getInstance().getUserAndPermission();
    }
}
