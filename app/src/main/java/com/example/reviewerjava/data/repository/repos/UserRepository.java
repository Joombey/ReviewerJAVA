package com.example.reviewerjava.data.repository.repos;

import com.example.reviewerjava.data.room.models.UserEntity;

public interface UserRepository {
    void updateUser(UserEntity user);
    UserEntity getUserById(int userId);
}
