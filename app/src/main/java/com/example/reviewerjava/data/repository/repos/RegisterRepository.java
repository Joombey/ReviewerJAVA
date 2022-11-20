package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.room.models.PermissionEntity;

public interface RegisterRepository {
    boolean login(String login, String password);
    PermissionEntity getPermission(String role);
}
