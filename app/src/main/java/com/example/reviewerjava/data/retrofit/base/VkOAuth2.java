package com.example.reviewerjava.data.retrofit.base;

import com.example.reviewerjava.BuildConfig;
import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.retrofit.response.VkResponse;
import com.example.reviewerjava.data.retrofit.service.VkService;
import com.example.reviewerjava.data.room.models.UserEntity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VkOAuth2 {
    private VkService vkService;
    public VkOAuth2(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        vkService = retrofit.create(VkService.class);
    }

    public void getUserData(File parentFilePath){
        vkService.getUser(
                BuildConfig.VK_API_VERSION,
                CurrentUser.getInstance().access_token,
                CurrentUser.getInstance().getUserId
        ).enqueue(
                new Callback<VkResponse>() {
                    @Override
                    public void onResponse(Call<VkResponse> call, Response<VkResponse> response) {
                        if(response.isSuccessful()){
                            response.body().loadUserImage(
                                    parentFilePath,
                                    response.body().response.get(0).imageUri
                            );
                            RepositoryController.addNewUser(
                                    response.body().getUserEntityInstance()
                            );
                        }
                    }

                    @Override
                    public void onFailure(Call<VkResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                }
        );
    }
}
