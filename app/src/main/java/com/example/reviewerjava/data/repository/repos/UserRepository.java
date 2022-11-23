package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.room.models.UserEntity;

import java.util.List;

public interface UserRepository {
    void updateUser(UserEntity user);
    UserEntity getUserByName(String userName);
    LiveData<List<UserEntity>> getUsers(String name);
    void ban(UserEntity user);
    void addNewUser(UserEntity newUser);
    boolean userExists(String name);
}
