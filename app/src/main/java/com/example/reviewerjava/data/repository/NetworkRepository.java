package com.example.reviewerjava.data.repository;

import com.example.reviewerjava.data.retrofit.base.ReviewerApiBase;
import com.example.reviewerjava.di.ServiceLocator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.http.Url;

public class NetworkRepository {
    public void signIn(String login, String password){
        ServiceLocator.getInstance().getReviewerApi().signIn(login, password);
    }
    public void downloadFromInternet(String resource, File newFile){
        new Thread(() ->{
            try {
                ServiceLocator.getInstance().getResourceRepository().save(new URL(resource).openStream(), new FileOutputStream(newFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }
}
