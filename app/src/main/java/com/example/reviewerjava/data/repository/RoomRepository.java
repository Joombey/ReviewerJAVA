package com.example.reviewerjava.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;
import com.example.reviewerjava.data.room.ReviewListRoomDataBase;
import com.example.reviewerjava.data.room.daos.ReviewDAO;

import java.util.List;

public class RoomRepository implements ReviewListRepository, AddReviewRepository, RegisterRepository {
    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>(false);
    private LiveData<List<Review>> mReviewList = new MutableLiveData<>();
    private ReviewDAO mReviewDAO;

    public RoomRepository(Application application){
        ReviewListRoomDataBase db = ReviewListRoomDataBase.getDatabase(application);
        mReviewDAO = db.reviewDAO();
        mReviewList = mReviewDAO.getAllReviews();
    }

    @Override
    public LiveData<List<Review>> getReviewList() {
        return mReviewList;
    };

    @Override
    public  void addReview(Review review) {
        mReviewDAO.insertReview(review);
    }

    @Override
    public MutableLiveData<Boolean> isLoggedIn() {
        return loggedIn;
    }

    @Override
    public boolean login(String login, String password) {
        if(login.equals("admin") && password.equals("admin")){
            loggedIn.setValue(true);
            return true;
        } else return false;
    }

    @Override
    public void logOut() {
        loggedIn.setValue(false);
    }
}
