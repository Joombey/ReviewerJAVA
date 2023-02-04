package com.example.reviewerjava.data.repository.repos;

import com.example.reviewerjava.data.retrofit.request.UserRequest;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

public interface AuthRepository {
    boolean signIn(String login, String password);
    boolean signUp(UserRequest userRequest);
}