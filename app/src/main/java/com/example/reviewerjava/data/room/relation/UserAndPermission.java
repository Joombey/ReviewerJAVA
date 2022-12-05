package com.example.reviewerjava.data.room.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.google.gson.annotations.SerializedName;

public class UserAndPermission {
    @Embedded
    @SerializedName("user")
    public UserEntity user;

    @Relation(
            parentColumn = "role",
            entityColumn = "role"
    )
    @SerializedName("permission")
    public PermissionEntity permission;

    public UserAndPermission(UserEntity user, PermissionEntity permission){
        this.user = user;
        this.permission = permission;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }
}
