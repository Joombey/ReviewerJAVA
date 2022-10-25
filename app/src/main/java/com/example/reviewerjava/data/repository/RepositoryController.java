package com.example.reviewerjava.data.repository;

import android.app.Application;

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

import java.util.List;

public class RepositoryController {
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
        return shoppingQuery;
    }
}
