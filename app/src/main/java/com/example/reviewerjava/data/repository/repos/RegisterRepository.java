package com.example.reviewerjava.data.repository.repos;

import com.example.reviewerjava.data.room.relation.UserAndPermission;

public interface RegisterRepository {
    boolean signIn(String login, String password);
    boolean signUp(String login, String password);
    UserAndPermission getUserAndPermission(String login);
}
