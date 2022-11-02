package com.example.reviewerjava.data.repository;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;
import com.example.reviewerjava.data.room.ReviewListRoomDataBase;
import com.example.reviewerjava.data.room.daos.ReviewDAO;
import com.example.reviewerjava.data.room.models.ReviewEntity;

import java.util.List;

public class RoomRepository implements ReviewListRepository, AddReviewRepository, RegisterRepository {
    private MutableLiveData<Boolean> loggedIn;
    private LiveData<List<ReviewEntity>> mReviewList;
    private ReviewDAO mReviewDAO;

    public RoomRepository(Application application){
        loggedIn = new MutableLiveData<>(false);
        ReviewListRoomDataBase db = ReviewListRoomDataBase.getDatabase(application);
        mReviewDAO = db.reviewDAO();
        mReviewList = mReviewDAO.getAllReviews();
    }

    @Override
    public <T extends Review> void addReview(T review) {
        ReviewListRoomDataBase.databaseWriteExecutor.execute(() ->{
            mReviewDAO.insertReview(ReviewEntity.getInstance(review));
        });
    }

    public LiveData<List<ReviewEntity>> getReviewList() {
        return mReviewList;
    }

    @Override
    public MutableLiveData<Boolean> isLoggedIn() {
        return loggedIn;
    }

    @Override
    public boolean login(String login, String password) {
        if(login.equals("admin") && password.equals("admin")){
            loggedIn.setValue(true);
        }
        return true;
    }

    @Override
    public void logOut() {
        loggedIn.setValue(false);
    }

    @Override
    public ReviewEntity getReviewById(int id) {
        return mReviewDAO.getReviewById(id);
    }
}
