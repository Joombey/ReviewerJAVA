package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
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
}
