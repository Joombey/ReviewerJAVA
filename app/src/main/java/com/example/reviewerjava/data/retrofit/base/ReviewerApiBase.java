package com.example.reviewerjava.data.retrofit.base;


import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.BuildConfig;
import com.example.reviewerjava.data.model.User;
import com.example.reviewerjava.data.repository.RepositoryController;

import com.example.reviewerjava.data.retrofit.request.ReviewRequest;
import com.example.reviewerjava.data.retrofit.request.UserRequest;

import com.example.reviewerjava.data.retrofit.request.pks.UserId;
import com.example.reviewerjava.data.retrofit.response.ReviewerReviewResponse;
import com.example.reviewerjava.data.retrofit.service.ReviewerService;

import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.example.reviewerjava.di.ServiceLocator;

import java.io.File;
import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

public class ReviewerApiBase {
    private static boolean result;
    private ReviewerService api;
    public ReviewerApiBase(){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain ->  {
                    Request request = chain.request().newBuilder()
                            .addHeader("Host", "Mobile-Tester")
                            .build();
                    return chain.proceed(request);
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://127.0.0.1:8080")
                .client(client)
                .build();

        api = retrofit.create(ReviewerService.class);
    }

    public boolean signIn(String login, String password) {
        result = false;
        api.auth(login, password).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if(response.isSuccessful()) {
                    RepositoryController.addToLocalIfRequired(response.body());
                    result = true;
                }
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result;
    }


    public boolean signUp(UserRequest user) {
        MutableLiveData<Boolean> result = new MutableLiveData<>(false);
        api.signUp(user).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if(response.isSuccessful()){
                    result.setValue(true);
                    ServiceLocator.getInstance().getLocalBase().addUserAndPermission(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result.getValue();
    }




    public boolean uploadFileToServer(File file, UserId user){

        MutableLiveData<Boolean> result = new MutableLiveData<>(false);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData(
                "file",
                file.getName(),
                RequestBody.create(MediaType.parse("image/*"), file)
        );

        api.uploadToServer(
                filePart,
                user
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                     result.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
            }
        });
        return result.getValue();
    }

    public void createNewReview(ReviewEntity review){
        api.newReview(review).enqueue(new Callback<ReviewerReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewerReviewResponse> call, Response<ReviewerReviewResponse> response) {
                if (response.isSuccessful()){
                    review.id = response.body().id.id;
                    ServiceLocator.getInstance().getLocalBase().addReview(review);
                }
            }

            @Override
            public void onFailure(Call<ReviewerReviewResponse> call, Throwable t) {

            }
        });
    }
}
