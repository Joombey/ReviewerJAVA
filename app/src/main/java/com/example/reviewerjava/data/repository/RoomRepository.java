package com.example.reviewerjava.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.model.Permission;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;
import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.room.ReviewListRoomDataBase;
import com.example.reviewerjava.data.room.daos.ReviewDAO;
import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;

import java.util.List;

public class RoomRepository implements ReviewListRepository, AddReviewRepository, RegisterRepository, UserRepository {
    private LiveData<List<ReviewEntity>> mReviewList;
    private ReviewDAO mReviewDAO;

    public RoomRepository(Application application){
        ReviewListRoomDataBase db = ReviewListRoomDataBase.getDatabase(application);
        mReviewDAO = db.reviewDAO();
        mReviewList = mReviewDAO.getAllReviews();

        PermissionEntity permission = new Permission.Builder()
                .role("admin")
                .profileAccess(true)
                .reviewMakerAccess(true)
                .roleChangerAccess(Permission.ACCESS)
                .userBanAccess(true)
                .build()
                .getPermissionEntityInstance();

        Log.i("DATA123", permission.role + "");
        ReviewListRoomDataBase.databaseWriteExecutor.execute(()->{
            mReviewDAO.insertPermission(permission);
        });

        ReviewListRoomDataBase.databaseWriteExecutor.execute(()->{
            mReviewDAO.insertPermission(new Permission.Builder()
                    .role("moder")
                    .profileAccess(true)
                    .reviewMakerAccess(true)
                    .reviewBlockAccess(true)
                    .build()
                    .getPermissionEntityInstance());
        });

        ReviewListRoomDataBase.databaseWriteExecutor.execute(()->{
            mReviewDAO.insertPermission(new Permission.Builder()
                    .role("user")
                    .profileAccess(true)
                    .reviewMakerAccess(true)
                    .roleChangerAccess(Permission.DENY)
                    .userBanAccess(true)
                    .build()
                    .getPermissionEntityInstance());
        });

        ReviewListRoomDataBase.databaseWriteExecutor.execute(()->{
            mReviewDAO.insertPermission(new Permission.Builder()
                    .role("unauthorized")
                    .build()
                    .getPermissionEntityInstance());
        });
    }

    @Override
    public void addReview(ReviewEntity review) {
        ReviewListRoomDataBase.databaseWriteExecutor.execute(() ->{
            mReviewDAO.insertReview(review);
        });
    }

    public LiveData<List<ReviewEntity>> getReviewList() {
        return mReviewList;
    }

    @Override
    public boolean login(String login, String password) {
        return login.equals("admin") && password.equals("admin")
                || login.equals("moder") && password.equals("moder")
                || login.equals("user") && password.equals("user");
    }

    @Override
    public ReviewEntity getReviewById(int id) {
        return mReviewDAO.getReviewById(id);
    }

    @Override
    public LiveData<List<ReviewEntity>> getReviewsByUserId(int userId) {
        return mReviewDAO.getReviewsByUserId(userId);
    }

    @Override
    public void updateUser(UserEntity user) {
        ReviewListRoomDataBase.databaseWriteExecutor.execute(()->mReviewDAO.updateUserState(user));
    }

    @Override
    public UserEntity getUserById(int userId) {
        return mReviewDAO.getUserById(userId);
    }

    @Override
    public PermissionEntity getPermission(String role) {
        return mReviewDAO.getPermission(role);
    }
}
