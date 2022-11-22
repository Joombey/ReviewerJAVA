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
    public Boolean reviewMakerAccess;
    public Boolean profileAccess;
    public Boolean reviewBlockAccess;
    public Boolean roleChangerAccess;

    public PermissionEntity(@NonNull String role,
                            Boolean reviewMakerAccess,
                            Boolean profileAccess,
                            Boolean reviewBlockAccess,
                            Boolean roleChangerAccess) {
        this.role = role;
        this.reviewMakerAccess = reviewMakerAccess;
        this.profileAccess = profileAccess;
        this.reviewBlockAccess = reviewBlockAccess;
        this.roleChangerAccess = roleChangerAccess;
    }
}
