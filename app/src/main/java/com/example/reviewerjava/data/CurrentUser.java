package com.example.reviewerjava.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Permission;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

public class CurrentUser {
    private static CurrentUser instance;

    public static final UserAndPermission UNAUTHORIZED_USER = new UserAndPermission(
            new UserEntity(UserEntity.UNAUTHORIZED, null, null),
            new Permission.Builder().build().getPermissionEntityInstance()
    );
    private final MutableLiveData<UserAndPermission> userAndPermission = new MutableLiveData<>(UNAUTHORIZED_USER);
    public String access_token;
    public String getUserId;

    CurrentUser(){}

    public static CurrentUser getInstance() {
        if (instance == null){
            instance = new CurrentUser();
        }
        return instance;
    }

    public LiveData<UserAndPermission> getUserAndPermission(){
        return this.userAndPermission;
    }

    public void setUserAndPermission(UserAndPermission userAndPermission) {
        if(userAndPermission == null){
            this.userAndPermission.setValue(UNAUTHORIZED_USER);
        }else this.userAndPermission.setValue(userAndPermission);
    }
}
