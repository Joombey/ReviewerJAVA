package com.example.reviewerjava.data.retrofit.response;


import com.example.reviewerjava.data.room.models.UserEntity;
import com.google.gson.annotations.SerializedName;

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

    public UserEntity getUserEntityInstance() {
        if(response.get(0).userCity.title == null){
            response.get(0).userCity.title = "";
        }

        return new UserEntity(
                response.get(0).name,
                "",
                response.get(0).userCity.title,
                UserEntity.USER
        );
    }

    public static UserEntity convertToUserEntity(User vkUser){
        if(vkUser.userCity.title == null){
            vkUser.userCity.title = "";
        }

        return new UserEntity(
                vkUser.name,
                "",
                vkUser.imageUri,
                UserEntity.USER
        );
    }

}
