package com.example.reviewerjava.data.repository.sources;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.room.daos.UserDao;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

public interface UserSourceOfTruth {

    UserEntity getUserByName(String userName);


    void updateUserState(UserEntity user);


    void insertUser(UserEntity user);


    LiveData<List<UserEntity>> getUsers(String name);


    void deleteUser(UserEntity user);


    UserAndPermission getUser(String name);
}
