package com.example.reviewerjava.data.room.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Permission;
import com.example.reviewerjava.data.model.User;

@Entity(
        tableName = "users",
        foreignKeys = @ForeignKey(
                entity = PermissionEntity.class,
                parentColumns = "role",
                childColumns = "role"
        )
)
public class UserEntity extends User {

    public static final String ADMIN = "admin";
    public static final String MODERATOR = "moder";
    public static final String USER = "user";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    @ColumnInfo(defaultValue = "NT")
    private String city;
    private String avatar;
    @ColumnInfo(defaultValue = "user")
    private String role;


    public UserEntity(String name, String city, String avatar) {
        super(name, city, avatar);
        this.name = name;
        this.avatar = avatar;
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
