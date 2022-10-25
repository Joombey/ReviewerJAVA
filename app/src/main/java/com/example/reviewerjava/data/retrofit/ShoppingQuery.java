package com.example.reviewerjava.data.retrofit;


import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Item;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShoppingQuery {

    private ProductSearchService api;

    public ShoppingQuery(OkHttpClient client){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        api = retrofit.create(ProductSearchService.class);
    }

    public List<Item> getItemsByRequest(String q){
        List<Item> items = new ArrayList<>();
        api.getShoppingList(q).enqueue(new Callback<ShoppingResponse>() {
            @Override
            public void onResponse(Call<ShoppingResponse> call, Response<ShoppingResponse> response) {
                items.addAll(response.body().shoppingResultList.stream().map(items -> {
                    Item item = new Item();
                    item.setItemName(items.getItemName());
                    return item;
                }).collect(Collectors.toList()));
            }

            @Override
            public void onFailure(Call<ShoppingResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return items;
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

            public Product(String title, String productId, String source, String imageUrl, Context context) {
                this.title = title;
                this.productId = productId;
                this.source = source;
                this.imageUrl = imageUrl;

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            cache(context);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.run();
                try {
                    cache(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void cache(Context context) throws IOException {
                URL url = new URL(imageUrl);
                InputStream inputStream = url.openStream();

                createFile(context.getCacheDir(), inputStream);
            }

            public void moveToMedia(Context context) throws IOException {
                File imageFile = new File(Uri.parse(imageUrl).toString());
                InputStream inputStream = new FileInputStream(imageFile);

                createFile(context.getExternalMediaDirs()[0], inputStream);
                context.getCacheDir().delete();
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
                return title + "-" + productId + ".png";
            }
        }
    }
}