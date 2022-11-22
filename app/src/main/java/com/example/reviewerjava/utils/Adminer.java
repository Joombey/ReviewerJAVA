package com.example.reviewerjava.utils;

import com.example.reviewerjava.data.room.models.UserEntity;

public interface Adminer {
    void ban(UserEntity user);
    void updateUser(UserEntity user, String newRole);
    void checkUser(UserEntity user);
}
