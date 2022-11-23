package com.example.reviewerjava.data.retrofit.service;

import com.example.reviewerjava.data.retrofit.response.VkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface VkService {
    @GET("users.get?fields=photo_200,city,screen_name")
    Call<VkResponse> getUser(
            @Query("v") String version,
            @Query("access_token") String token,
            @Query("user_ids") String userId
    );
}
