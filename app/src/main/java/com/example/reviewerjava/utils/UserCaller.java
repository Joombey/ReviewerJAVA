package com.example.reviewerjava.utils;

import android.view.View;

import com.example.reviewerjava.data.room.models.UserEntity;

public interface UserCaller {
    UserEntity getUserById(int id);
}
