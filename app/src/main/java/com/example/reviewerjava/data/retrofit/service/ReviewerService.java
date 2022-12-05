package com.example.reviewerjava.data.retrofit.service;


import com.example.reviewerjava.data.retrofit.request.ReviewRequest;
import com.example.reviewerjava.data.retrofit.request.UserRequest;
import com.example.reviewerjava.data.retrofit.request.pks.UserId;
import com.example.reviewerjava.data.retrofit.response.ReviewerReviewResponse;
import com.example.reviewerjava.data.retrofit.response.UserAndPermissionResponse;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ReviewerService {
    @POST("/auth/sign-in")
    Call<UserAndPermission> auth(@Body String login, @Body String password);

    @POST("/auth/sign-up")
    Call<UserAndPermission> signUp(
            @Body UserRequest user
    );

    @Multipart
    @POST("/upload/{fileName}")
    Call<ResponseBody> uploadToServer(
            @Part MultipartBody.Part filePart,
            @Part("id") UserId userInfo
    );

    @POST("/new-review")
    Call<ReviewerReviewResponse> newReview(
            @Body ReviewEntity review
    );
}
