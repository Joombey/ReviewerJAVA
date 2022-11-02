package com.example.reviewerjava.data.room.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.UserEntity;

public class UserAndPermission {
    @Embedded
    public UserEntity user;

    @Relation(
            parentColumn = "role",
            entityColumn = "role"
    )
    public PermissionEntity permission;

    public UserAndPermission(UserEntity user, PermissionEntity permission){
        this.user = user;
        this.permission = permission;
    }
}
