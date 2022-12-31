package com.example.reviewerjava.data.retrofit.base;

import android.app.Service;
import android.net.Uri;

import com.example.reviewerjava.BuildConfig;
import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.retrofit.response.VkResponse;
import com.example.reviewerjava.data.retrofit.service.VkService;
import com.example.reviewerjava.di.ServiceLocator;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VkApiBase {
    private final VkService vkService;
    public VkApiBase(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        vkService = retrofit.create(VkService.class);
    }

    public void getUserInfo(File parentFilePath){
        vkService.getUser(
                BuildConfig.VK_API_VERSION,
                CurrentUser.getInstance().access_token,
                CurrentUser.getInstance().userId
        ).enqueue(
                new Callback<VkResponse>() {
                    @Override
                    public void onResponse(Call<VkResponse> call, Response<VkResponse> response) {
                        if(response.isSuccessful()){
                            ServiceLocator.getInstance().getResourceRepository().addToLocalIfRequired(
                                    response.body().response.get(0)
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
