package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.User;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

public class NavigationViewModel extends ViewModel {
    public LiveData<UserAndPermission> getCurrentUser() {
        return CurrentUser.getInstance().getUserAndPermission();
    }

    public void setCurrentUser(UserAndPermission userAndPermission) {
        CurrentUser.getInstance().setUserAndPermission(userAndPermission);
    }

    public void setCurrentUser(String userName) {
        CurrentUser.getInstance().setUserAndPermission(userName);
    }

    public UserEntity getUserByName(String name){
        return RepositoryController.getUserByName(name);
    }

}
