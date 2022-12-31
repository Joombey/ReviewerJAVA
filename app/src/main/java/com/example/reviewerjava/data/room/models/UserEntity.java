package com.example.reviewerjava.data.room.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Permission;
import com.example.reviewerjava.data.model.User;

import okhttp3.MediaType;

@Entity(
        tableName = "users",
        foreignKeys = @ForeignKey(
                entity = PermissionEntity.class,
                parentColumns = "role",
                childColumns = "role"
        )
)
public class UserEntity extends User {

    @Ignore
    public static final String ADMIN = "admin";
    @Ignore
    public static final String MODERATOR = "moder";
    @Ignore
    public static final String USER = "user";
    @Ignore
    public static final String UNAUTHORIZED = "unauthorized";

    @NonNull
    @PrimaryKey
    private String name;
    @ColumnInfo(defaultValue = "NT")
    private String city;
    private String avatar;
    @ColumnInfo(defaultValue = "user")
    private String role;


    @Ignore
    public UserEntity(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
        this.city = "";
    }

    public UserEntity(@NonNull String name, String city, String avatar, String role) {
        this.name = name;
        this.city = city;
        this.avatar = avatar;
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        super.setName(this.name);
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
        super.setCity(city);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
