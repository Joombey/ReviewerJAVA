package com.example.reviewerjava.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<Boolean> getRepository(){
        return RepositoryController.getRegisterRepository().isLoggedIn();
    }
    public boolean logIn(String login, String password){
        return RepositoryController.getRegisterRepository().login(login, password);
    }
    public void logOut(){
        RepositoryController.getRegisterRepository().logOut();
    }

    public LiveData<UserAndPermission> getUser(){
        return CurrentUser.getInstance().getUserAndPermission();
    }

    public void setCurrentUser(UserAndPermission user){
        CurrentUser.getInstance().setUserAndPermission(user);
    }
}
