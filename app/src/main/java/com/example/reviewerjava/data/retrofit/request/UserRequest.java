package com.example.reviewerjava.data.retrofit.request;

import com.example.reviewerjava.data.retrofit.request.pks.UserId;

public class UserRequest {
    public UserId id;

    public String city;
    public String role;
    public String avatar;

    public UserRequest(UserId id, String city, String avatar) {
        this.id = id;
        this.city = city;
        this.avatar = avatar;
    }
}
