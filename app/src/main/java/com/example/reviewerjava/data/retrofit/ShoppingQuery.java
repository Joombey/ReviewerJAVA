package com.example.reviewerjava.data.retrofit;



import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.BuildConfig;
import com.example.reviewerjava.data.model.Item;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ShoppingQuery {

    private ProductSearchService api;

    public ShoppingQuery(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
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
        MutableLiveData <List<Item>> shoppingList = new MutableLiveData<>();
        api.getShoppingList(q, BuildConfig.API_KEY).enqueue(new Callback<ShoppingResponse>() {
            @Override
            public void onResponse(Call<ShoppingResponse> call, Response<ShoppingResponse> response) {
                if (response.isSuccessful()) {
                    shoppingList.setValue(
                            response.body().shoppingResultList.stream().map(entryItem -> {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            entryItem.cache(cacheDir);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                Item outItem = new Item(
                                        entryItem.source,
                                        entryItem.title,
                                        entryItem.imageUrl,
                                        entryItem.description,
                                        entryItem.productId
                                );
                                return outItem;
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

    class ShoppingResponse{
        @SerializedName("shopping_results")
        public List<Product> shoppingResultList;

        class Product{
            public String title;
            @SerializedName("product_id")
            public String productId;
            public String source;
            @SerializedName("thumbnail")
            public String imageUrl;
            @SerializedName("snippet")
            public String description;

            public void cache(File cacheDir) throws IOException {
                URL url = new URL(imageUrl);
                InputStream inputStream = url.openStream();
                createFile(cacheDir, inputStream);
            }

             private void createFile(File parent, InputStream inputStream) throws IOException {
                 if(!parent.exists()) parent.mkdir();
                 OutputStream outputStream = new FileOutputStream(new File(parent, getImageNamePattern()));

                 byte[] buffer = new byte[1024*50];
                 int bytesRead = 0;
                 while((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0){
                     outputStream.write(buffer, 0, bytesRead);
                 }

                 imageUrl = Uri.fromFile(new File(parent, getImageNamePattern())).toString();
                 inputStream.close();
                 outputStream.close();
             }

            private String getImageNamePattern(){
                return productId + ".png";
            }
        }
    }
}