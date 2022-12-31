package com.example.reviewerjava.data.retrofit.response;

import android.net.Uri;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.repository.RoomRepository;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class ShoppingResponse{
    @SerializedName("shopping_results")
    public List<Product> shoppingResultList;

    public class Product{
        public String title;
        @SerializedName("product_id")
        public String productId;
        public String source;
        @SerializedName("thumbnail")
        public String imageUrl;
        @SerializedName("snippet")
        public String description;

        public void cacheItemImage(File cacheDir) {
            new Thread(()->{
                try {
                    URL url = new URL(imageUrl);
                    InputStream inputStream = url.openStream();
                    createFile(cacheDir, inputStream);
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }).start();
        }

        private void createFile(File parent, InputStream inputStream) throws IOException {
            if(!parent.exists()) parent.mkdir();
            OutputStream outputStream = new FileOutputStream(new File(parent, getImageNamePattern()));

            byte[] buffer = new byte[1024*50];
            int bytesRead;
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

        public Item getItemInstance(){
            return new Item(
                    this.source,
                    this.title,
                    this.imageUrl,
                    this.description,
                    this.productId
            );
        }
    }
}
