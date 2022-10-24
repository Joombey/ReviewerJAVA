package com.example.reviewerjava.data.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductSearchService {
    @GET("search?tbm=shop&")
    Call<ShoppingQuery.ShoppingResponse> getShoppingList(@Query("tbm") String title);
}
