package com.example.reviewerjava.data.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;
import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.retrofit.ShoppingQuery;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class RepositoryController extends Thread{
    static RoomRepository repo;
    static ReviewListRepository reviewListRepository;
    static RegisterRepository registerRepository;
    static AddReviewRepository addReviewRepository;
    static UserRepository userRepository;
    static ShoppingQuery shoppingQuery;

    public static void init(Application application){
        repo = new RoomRepository(application);
        shoppingQuery = new ShoppingQuery();
        reviewListRepository = repo;
        registerRepository = new RoomRepository(application);
        addReviewRepository = repo;
        userRepository = repo;
    }

    public static LiveData<List<ReviewEntity>> getReviewList(){
        return reviewListRepository.getReviewList();
    }

    public static void addReview(ReviewEntity review){
        addReviewRepository.addReview(review);
    }

    public static UserEntity getUserById(int userId){
        return userRepository.getUserById(userId);
    }

    public static LiveData<UserAndPermission> getCurrentUserData() {
        return CurrentUser.getInstance().getUserAndPermission();
    }

    public static int getCurrentUserId(){
        return CurrentUser.getInstance().getUserAndPermission().getValue().user.getId();
    }

    public static void updateUser(UserEntity user){
        userRepository.updateUser(user);
    }

    public static ReviewEntity getReviewById(int id){
        return reviewListRepository.getReviewById(id);
    }

    public static LiveData<List<ReviewEntity>> getReviewsByUserId(int userId){
        return reviewListRepository.getReviewsByUserId(userId);
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

    public static boolean login(String login, String password) {
        UserEntity user;
        if (repo.login(login, password)){
            user = new UserEntity(login, "NT", "");
            user.setRole(login);
        } else return false;

        UserAndPermission userAndPermission = new UserAndPermission(
                user,
                repo.getPermission(user.getRole())
        );
        CurrentUser.getInstance().setUserAndPermission(userAndPermission);
        return true;
    }

    public static void logOut() {
        CurrentUser.getInstance().setUserAndPermission(
                CurrentUser.UNAUTHORIZED_USER
        );
    }
}
