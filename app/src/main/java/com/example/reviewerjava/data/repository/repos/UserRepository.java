package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

public interface UserRepository {
    UserEntity getUserByName(String userName);

    LiveData<List<UserEntity>> getUsersExcept(String userName);

    UserAndPermission getUserAndPermission(String name);

    void addNewUser(UserEntity newUser);

    void addUserAndPermission(UserAndPermission userAndPermission);

    void addUserList(List<UserAndPermission> body);

    void ban(UserEntity user);

    void updateUser(UserEntity user);

    boolean userExists(String name);
}
