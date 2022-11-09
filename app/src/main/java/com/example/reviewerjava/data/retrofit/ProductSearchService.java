package com.example.reviewerjava.data.retrofit;

import com.example.reviewerjava.data.model.Item;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductSearchService {
    @GET("search.json?tbm=shop&engine=google")
    Call<ShoppingQuery.ShoppingResponse> getShoppingList(@Query("q") String q);

    @GET("asdsad")
    Call<Item> getItemData(@Query("productId") String productId);
}