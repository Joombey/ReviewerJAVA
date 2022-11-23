package com.example.reviewerjava.data.retrofit.response;

import android.net.Uri;
import android.util.Log;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.Permission;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class VkResponse {
    public class User{
        @SerializedName("screen_name")
        public String name;
        @SerializedName("photo_200")
        public String imageUri;
        @SerializedName("city")
        public City userCity;

        public int userId;
        public String access_token;

        public User(String access_token, int userId){
            this.access_token = access_token;
            this.userId = userId;
        }
    }

    public class City{
        public String title;
    }
    public List<User> response;

    public void loadUserImage(File parent, String url){
        new Thread(()->{
            try {
                Log.i("URL", url);
                InputStream inputStream = new URL(url).openStream();
                if(!parent.exists()) parent.mkdir();
                File outFile = new File(parent, response.get(0).name);
                OutputStream outputStream = new FileOutputStream(new File(parent, response.get(0).name));

                byte[] buffer = new byte[1024*50];
                int bytesRead;
                while((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0){
                    outputStream.write(buffer, 0, bytesRead);
                }

                response.get(0).imageUri = Uri.fromFile(outFile).toString();
                inputStream.close();
                outputStream.close();
                UserEntity user = RepositoryController.getUserByName(getUserEntityInstance().getName());
                user.setAvatar(response.get(0).imageUri);
                RepositoryController.updateUser(user);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public UserEntity getUserEntityInstance() {
        return new UserEntity(
                response.get(0).name,
                ""
        );
    }
}
