package com.example.reviewerjava.data.repository;

import android.app.Application;
import android.net.Uri;
import android.webkit.WebViewClient;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.repository.repos.AddReviewRepository;
import com.example.reviewerjava.data.repository.repos.RegisterRepository;
import com.example.reviewerjava.data.repository.repos.ReportRepository;
import com.example.reviewerjava.data.repository.repos.ReviewListRepository;
import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.retrofit.OAuth2;
import com.example.reviewerjava.data.retrofit.base.ReviewerApiBase;
import com.example.reviewerjava.data.retrofit.base.ShoppingApiBase;
import com.example.reviewerjava.data.retrofit.base.VkApiBase;
import com.example.reviewerjava.data.retrofit.request.UserRequest;
import com.example.reviewerjava.data.retrofit.request.pks.UserId;
import com.example.reviewerjava.data.retrofit.response.VkResponse;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.example.reviewerjava.di.ServiceLocator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class RepositoryController{
    static RoomRepository repo;
    static ReviewListRepository reviewListRepository;
    static RegisterRepository registerRepository;
    static AddReviewRepository addReviewRepository;
    static UserRepository userRepository;
    static ShoppingApiBase shoppingApiBase;
    static VkApiBase vkApiBase;
    static ReportRepository reportRepository;
    static ReviewerApiBase reviewerApi;

    public static void init(Application application){
        ServiceLocator.getInstance().init(application);
        repo = new RoomRepository(application);
        shoppingApiBase = new ShoppingApiBase();
        vkApiBase = new VkApiBase();
        reviewerApi = new ReviewerApiBase();
        reviewListRepository = repo;
        addReviewRepository = repo;
        userRepository = repo;
        reportRepository = repo;
    }

    public static LiveData<List<ReviewEntity>> getReviewList(){
        return reviewListRepository.getReviewList();
    }

    public static void addReview(ReviewEntity review){
        reviewerApi.createNewReview(review);
    }

    public static UserEntity getUserByName(String userName){
        return userRepository.getUserByName(userName);
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
        return shoppingApiBase.getItemsByRequest(query, cacheDir);
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

            save(inputStream, outputStream);

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
//        if (registerRepository.signIn(login, password)){
//            CurrentUser.getInstance().setUserAndPermission(registerRepository.getUserAndPermission(login));
//            return true;
//        }
//        return false;
        return reviewerApi.signIn(login, password);
    }

    public static void logOut() {
        CurrentUser.getInstance().setUserAndPermission(
                CurrentUser.UNAUTHORIZED_USER
        );
        CurrentUser.getInstance().userId = null;
        CurrentUser.getInstance().access_token = null;
    }

    public static boolean signUp(String login, String password, String city, String avatar) {
        UserRequest userRequest = new UserRequest(new UserId(login, password), city, avatar);
        return (reviewerApi.signUp(userRequest));
    }

    public static UserEntity getCurrentUser() {
        return CurrentUser.getInstance().getUserAndPermission().getValue().user;
    }

    public static ReviewAndUser getReviewAndUser(int reviewId) {
        return reviewListRepository.getReviewAndUserByReviewId(reviewId);
    }

    public static void ban(ReviewEntity review){
        reportRepository.ban(review);
        reviewerApi.blockReview(review.id);
    }

    public static void ban(UserEntity user){
        userRepository.ban(user);
        reviewerApi.banUser(user.getName());
    }

    public static void deny(ReportEntity report){
        reportRepository.deny(report);
        reviewerApi.denyReport(report.id);
    }

    public static void report(int id) {
        reportRepository.report(id);
        reviewerApi.report(id);
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

    public static void changeUserRole(UserEntity user, String newRole) {
//        user.setRole(newRole);
//        userRepository.updateUser(user);
        reviewerApi.changeRole(user.getName(), newRole);
    }

    public static void addToLocalIfRequired(UserEntity user) {
        if(userRepository.userExists(user.getName())){
            UserEntity aUser = userRepository.getUserByName(user.getName());
            user.setRole(aUser.getRole());
            changeUserRole(user);
        }else {
            userRepository.addNewUser(user);
        }
        CurrentUser.getInstance().setUserAndPermission(user.getName());
    }

    public static void changeUserRole(UserEntity user) {
        userRepository.updateUser(user);
    }

    public static void getUserInfo(File parentPath) {
        vkApiBase.getUserInfo(parentPath);
    }

    public static WebViewClient getClient(File parentPath, OAuth2.Back back) {
        return OAuth2.getWebViewClient(parentPath, back);
    }

    public static UserAndPermission getUserAndPermission(String name) {
        return userRepository.getUserAndPermission(name);
    }

    public static void save(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] buffer = new byte[1024 * 50];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadFromInternet(String resource, File newFile){
        new Thread(() ->{
            try {
                save(new URL(resource).openStream(), new FileOutputStream(newFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    public static void addToLocalIfRequired(VkResponse.User user) {
        if(!userRepository.userExists(user.name)){
            File file = new File(ServiceLocator.getInstance().getMediaDir().getPath(), user.name);
            downloadFromInternet(user.imageUri, file);

            user.imageUri = Uri.fromFile(file).toString();
            userRepository.addNewUser(VkResponse.convertToUserEntity(user));
        }
        CurrentUser.getInstance().setUserAndPermission(user.name);
    }

    public static void addToLocalIfRequired(UserAndPermission userAndPermission) {
        if (!repo.userExists(userAndPermission.user.getName())){
            File file = new File(ServiceLocator.getInstance().getCacheDir().getPath(), userAndPermission.user.getAvatar());

            downloadFromInternet(userAndPermission.user.getAvatar(), file);
            userAndPermission.user.setAvatar(Uri.fromFile(file).toString());

            repo.addUserAndPermission(userAndPermission);
        }
        CurrentUser.getInstance().setUserAndPermission(userAndPermission);
    }

    public static void addUserAndPermission(UserAndPermission body) {
        userRepository.addUserAndPermission(body);
        CurrentUser.getInstance().setUserAndPermission(body);
    }

    public static void getReviewsByName() {
        reviewerApi.fetchAllReview();
    }

    public static void updateReportList() {
        reviewerApi.getReportList();
    }

    public static void updateUserList() {
        reviewerApi.getUserList();
    }
}
