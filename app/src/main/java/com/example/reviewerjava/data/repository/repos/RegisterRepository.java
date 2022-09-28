package com.example.reviewerjava.data.repository.repos;

import androidx.lifecycle.MutableLiveData;

public interface RegisterRepository {
    MutableLiveData<Boolean> getLoggedIn();
    boolean login(String login, String password);
    void logOut();
}
