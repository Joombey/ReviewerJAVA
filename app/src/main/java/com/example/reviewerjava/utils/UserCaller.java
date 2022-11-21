package com.example.reviewerjava.utils;

import com.example.reviewerjava.data.room.models.UserEntity;

public interface UserCaller {
    UserEntity getUserByName(String userName);
}
