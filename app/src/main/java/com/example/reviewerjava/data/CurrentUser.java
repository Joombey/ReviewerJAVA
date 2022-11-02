package com.example.reviewerjava.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Permission;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

public class CurrentUser {
    private static CurrentUser instance;

    public static final UserAndPermission UNAUTHORIZED_USER = new UserAndPermission(
            null,
            new Permission.Builder().build().getPermissionEntityInstance()
    );
    private UserAndPermission userAndPermission;

    CurrentUser(){}

    public static CurrentUser getInstance() {
        if (instance == null){
            instance = new CurrentUser();
        }
        return instance;
    }

    public LiveData<UserAndPermission> getUserAndPermission(){
        return new MutableLiveData<>(userAndPermission);
    }

    public void setUserAndPermission(UserAndPermission userAndPermission) {
        this.userAndPermission = userAndPermission;
    }
}
