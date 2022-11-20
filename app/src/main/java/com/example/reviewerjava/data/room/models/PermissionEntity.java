package com.example.reviewerjava.data.room.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Permission;
import com.google.gson.Gson;

@Entity(
        tableName = "permissions"
)
public class PermissionEntity extends Permission {
    @NonNull
    @PrimaryKey
    public String role;
//    @ColumnInfo(defaultValue = "0", name = "review_maker")
    public Boolean reviewMakerAccess;
//    @ColumnInfo(defaultValue = "0", name = "profile")
    public Boolean profileAccess;
//    @ColumnInfo(defaultValue = "0", name = "review_block")
    public Boolean reviewBlockAccess;
//    @ColumnInfo(defaultValue = "0", name = "user_ban")
    public Boolean userBanAccess;
//    @ColumnInfo(defaultValue = "0", name = "role_change")
    public Boolean roleChangerAccess;
}
