package com.example.reviewerjava.data.repository;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.activity.result.ActivityResultRegistry;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.reviewerjava.data.mock.MockBase;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;
import com.example.reviewerjava.data.retrofit.ShoppingQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class RepositoryController extends Thread{
    static ReviewListRepository reviewListRepository;
    static RegisterRepository registerRepository;
    static AddReviewRepository addReviewRepository;
    static ShoppingQuery shoppingQuery;

    public static void init(Application application){
        reviewListRepository = new RoomRepository(application);
        //registerRepository = new RoomRepository(application);
        addReviewRepository = new RoomRepository(application);
    }

    public static ReviewListRepository getReviewListRepository() {
        if (reviewListRepository == null){
            reviewListRepository = new MockBase();
        }
        return reviewListRepository;
    }

    public static RegisterRepository getRegisterRepository(){
        if(registerRepository == null){
            registerRepository = new MockBase();
        }
        return registerRepository;
    }

    public static AddReviewRepository getAddReviewRepository(){
        if(addReviewRepository == null){
            addReviewRepository = new MockBase();
        }
        return addReviewRepository;
    }

    public static ShoppingQuery getShoppingQuery() {
        if (shoppingQuery == null){
            shoppingQuery = new ShoppingQuery();
        }
        return shoppingQuery;
    }

    public static Item moveToMedia(File parent, Item item) {
        new Thread(()->{
            Uri imageUri = Uri.parse(item.getItemImage());
            String imageFileName = imageUri.getLastPathSegment();
            File imageFile = new File(imageUri.getPath());
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(imageFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if(!parent.exists()) parent.mkdir();
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(new File(parent, imageFileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            byte[] buffer = new byte[1024*50];
            int bytesRead = 0;
            while(true){
                try {
                    if (!((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0)) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.write(buffer, 0, bytesRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            item.setItemImage(Uri.fromFile(new File(parent, imageFileName)).toString());
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            parent.delete();

        }).start();
//        Uri imageUri = Uri.parse(item.getItemImage());
//        String imageFileName = imageUri.getLastPathSegment();
//        File imageFile = new File(imageUri.getPath());
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(imageFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if(!parent.exists()) parent.mkdir();
//        OutputStream outputStream = new FileOutputStream(new File(parent, imageFileName));
//
//        byte[] buffer = new byte[1024*50];
//        int bytesRead = 0;
//        while((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0){
//            outputStream.write(buffer, 0, bytesRead);
//        }
//
//        item.setItemImage(Uri.fromFile(new File(parent, imageFileName)).toString());
//        inputStream.close();
//        outputStream.close();
//        parent.delete();

        return item;
    }
}
