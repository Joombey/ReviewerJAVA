package com.example.reviewerjava.data.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReportRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;
import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.retrofit.ShoppingQuery;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class RepositoryController{
    static RoomRepository repo;
    static ReviewListRepository reviewListRepository;
    static RegisterRepository registerRepository;
    static AddReviewRepository addReviewRepository;
    static UserRepository userRepository;
    static ShoppingQuery shoppingQuery;
    static ReportRepository reportRepository;

    public static void init(Application application){
        repo = new RoomRepository(application);
        shoppingQuery = new ShoppingQuery();
        reviewListRepository = repo;
        registerRepository = repo;
        addReviewRepository = repo;
        userRepository = repo;
        reportRepository = repo;
    }

    public static LiveData<List<ReviewEntity>> getReviewList(){
        return reviewListRepository.getReviewList();
    }

    public static void addReview(ReviewEntity review){
        addReviewRepository.addReview(review);
    }

    public static UserEntity getUserByName(String userName){
        return userRepository.getUserByName(userName);
    }

    public static LiveData<UserAndPermission> getCurrentUserData() {
        return CurrentUser.getInstance().getUserAndPermission();
    }

    public static String getCurrentUserName(){
        return CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
    }

    public static ReviewEntity getReviewById(int id){
        return reviewListRepository.getReviewById(id);
    }

    public static LiveData<List<ReviewEntity>> getReviewsName(String userName){
        return reviewListRepository.getReviewsByName(userName);
    }

    public static LiveData<List<Item>> getItemsByRequest(String query, File cacheDir){
        return shoppingQuery.getItemsByRequest(query, cacheDir);
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
                    assert inputStream != null;
                    if (!((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0)) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert outputStream != null;
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
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            parent.delete();
        }).start();
        return item;
    }

    public static boolean signIn(String login, String password) {
        if (registerRepository.signIn(login, password)){
            CurrentUser.getInstance().setUserAndPermission(registerRepository.getUserAndPermission(login));
            return true;
        }
        return false;
    }

    public static void logOut() {
        CurrentUser.getInstance().setUserAndPermission(
                CurrentUser.UNAUTHORIZED_USER
        );
    }

    public static void signUp(String login, String password) {
        repo.signUp(login, password);
    }

    public static UserEntity getCurrentUser() {
        return CurrentUser.getInstance().getUserAndPermission().getValue().user;
    }

    public static ReviewAndUser getReviewAndUser(int reviewId) {
        return reviewListRepository.getReviewAndUserByReviewId(reviewId);
    }

    public static void ban(ReviewEntity review){
        reportRepository.ban(review);
    }

    public static void ban(UserEntity user){
        userRepository.ban(user);
    }

    public static void deny(ReportEntity report){
        reportRepository.deny(report);
    }

    public static void report(int id) {
        reportRepository.report(id);
    }

    public static LiveData<List<ReportAndReview>> getReportList() {
        return reportRepository.getReports();
    }

    public static LiveData<List<UserEntity>> getUsers() {
        return userRepository.getUsers(
                CurrentUser.getInstance()
                .getUserAndPermission()
                .getValue()
                .user.getName()
        );
    }

    public static void updateUser(UserEntity user, String newRole) {
        user.setRole(newRole);
        userRepository.updateUser(user);
    }
}
