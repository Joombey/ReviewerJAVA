package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.UserEntity;

import java.util.List;

public class RoleChangerViewModel extends ViewModel {
    public LiveData<List<UserEntity>>getUsers(){
        return RepositoryController.getUsers();
    }

    public void updateUser(UserEntity user, String newRole){
        RepositoryController.updateUser(user, newRole);
    }


    public void ban(UserEntity user) {
        RepositoryController.ban(user);
    }
}
