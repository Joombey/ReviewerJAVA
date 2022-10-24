package com.example.reviewerjava.data.retrofit;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Shop;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShoppingQuery {

    private ProductSearchService api;

    public ShoppingQuery(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(new Converter.Factory() {
                }).build();
        api = retrofit.create(ProductSearchService.class);
    }

    public LiveData<List<String>> getItemsByRequest(){
        MutableLiveData<List<ShoppingResponse.Product>> items;
        api.getShoppingList("").enqueue(new Callback<ShoppingResponse>() {
            @Override
            public void onResponse(Call<ShoppingResponse> call, Response<ShoppingResponse> response) {
                response.body().shoppingResultList.stream().map(items -> items.title).collect(Collectors.toList());
            }

            @Override
            public void onFailure(Call<ShoppingResponse> call, Throwable t) {

            }
        });
        return null;
    }

    static class ShoppingResponse{
        @SerializedName("shopping_results")
        public List<Product> shoppingResultList;

         class Product extends Item{
            public String title;
            @SerializedName("product_id")
            public String productId;
            public String source;
            @SerializedName("thumbnail")
            public String imageUrl;

            public Product(String title, String productId, String source, String imageUrl) {
                this.title = title;
                this.productId = productId;
                this.source = source;
                this.imageUrl = imageUrl;
            }
             public void saveImage(Context context) throws IOException {
                URL url = new URL("file://some/path/anImage.png");
                InputStream input = url.openStream();
                File storagePath = Environment.getExternalStorageDirectory();
                try {
                    OutputStream output = new FileOutputStream (new File(storagePath,
                            "/"
                            + title
                            + "-"
                            + productId
                            + ".png"));
                    try {
                        byte[] buffer = new byte[1024 * 50];
                        int bytesRead = 0;
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } finally {
                        output.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    input.close();
                    imageUrl = Uri.fromFile(new File(storagePath, title + "-" + productId + ".png")).toString();

                }
            }
        }
    }
}
