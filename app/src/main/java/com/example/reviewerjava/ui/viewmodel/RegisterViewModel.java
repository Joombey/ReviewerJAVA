package com.example.reviewerjava.ui.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<Boolean> getRepository(){
        return RepositoryController.getRegisterRepository().getLoggedIn();
    }
    public boolean logIn(String login, String password){
        return RepositoryController.getRegisterRepository().login(login, password);
    }
    public void logOut(){
        RepositoryController.getRegisterRepository().logOut();
    }
}
