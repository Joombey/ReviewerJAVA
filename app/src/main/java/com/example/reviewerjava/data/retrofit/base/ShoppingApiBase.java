package com.example.reviewerjava.data.retrofit.base;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.reviewerjava.BuildConfig;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.repository.sources.ItemSourceOfTruth;
import com.example.reviewerjava.data.retrofit.response.ShoppingResponse;
import com.example.reviewerjava.data.retrofit.service.ProductSearchService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ShoppingApiBase {

    private ProductSearchService api;
    private MutableLiveData<List<Item>> shoppingList = new MutableLiveData<>();

    public ShoppingApiBase(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        HttpUrl newUrl = chain.request().url().newBuilder()
                                .addQueryParameter("api_key", BuildConfig.API_KEY)
                                .build();
                        Request request = chain.request().newBuilder().url(newUrl).build();
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://serpapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        api = retrofit.create(ProductSearchService.class);
    }

    public LiveData<List<Item>> getItemsByRequest(String q, File cacheDir){
        api.getShoppingList(q).enqueue(new Callback<ShoppingResponse>() {
            @Override
            public void onResponse(Call<ShoppingResponse> call, Response<ShoppingResponse> response) {

                if (response.isSuccessful()) {
                    shoppingList.setValue(
                            response.body().shoppingResultList.stream().map(entryItem -> {
                                entryItem.cacheItemImage(cacheDir);
                                return entryItem.getItemInstance();
                            }).collect(Collectors.toList())
                    );
                }
            }

            @Override
            public void onFailure(Call<ShoppingResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return shoppingList;
    }
}