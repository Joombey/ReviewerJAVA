package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

public class NavigationViewModel extends ViewModel {
    public LiveData<UserAndPermission> getCurrentUser() {
        return RepositoryController.getCurrentUserData();
    }
}
