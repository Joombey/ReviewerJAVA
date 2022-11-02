package com.example.reviewerjava.data.room.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Permission;

@Entity(
        tableName = "permissions"
)
public class PermissionEntity extends Permission {
    @NonNull
    @PrimaryKey
    public String role;
    @ColumnInfo(defaultValue = "FALSE", name = "review_maker")
    public boolean reviewMakerAccess;
    @ColumnInfo(defaultValue = "FALSE", name = "profile")
    public boolean profileAccess;
    @ColumnInfo(defaultValue = "FALSE", name = "review_block")
    public boolean reviewBlockAccess;
    @ColumnInfo(defaultValue = "FALSE", name = "user_ban")
    public boolean userBanAccess;
    @ColumnInfo(defaultValue = "FALSE", name = "role_change")
    public int roleChangerAccess;
}
