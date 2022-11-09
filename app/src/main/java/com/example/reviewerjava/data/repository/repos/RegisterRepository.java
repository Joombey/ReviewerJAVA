package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.room.models.PermissionEntity;

public interface RegisterRepository {
    MutableLiveData<Boolean> isLoggedIn();
    boolean login(String login, String password);
    void logOut();
    PermissionEntity getPermission(String role);
}
