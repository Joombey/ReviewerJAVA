package com.example.reviewerjava.data.retrofit.service;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.retrofit.response.ShoppingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductSearchService {
    @GET("search.json?tbm=shop&engine=google")
    Call<ShoppingResponse> getShoppingList(@Query("q") String q);

    @GET("asdsad")
    Call<Item> getItemData(@Query("productId") String productId);
}