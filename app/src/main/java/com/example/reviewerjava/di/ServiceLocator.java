package com.example.reviewerjava.di;

import android.app.Application;

import com.example.reviewerjava.data.repository.NetworkRepository;
import com.example.reviewerjava.data.repository.ResourceRepository;
import com.example.reviewerjava.data.repository.repos_impl.ReportRepositoryImpl;
import com.example.reviewerjava.data.repository.repos_impl.ReviewRepositoryImpl;
import com.example.reviewerjava.data.repository.repos_impl.UserRepositoryImpl;
import com.example.reviewerjava.data.repository.repos.ReportRepository;
import com.example.reviewerjava.data.repository.repos.ReviewRepository;
import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.retrofit.base.ReviewerApiBase;
import com.example.reviewerjava.data.retrofit.base.ShoppingApiBase;
import com.example.reviewerjava.data.retrofit.base.VkApiBase;
import com.example.reviewerjava.data.room.ReviewerRoomDb;


import java.io.File;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceLocator {
    private static ServiceLocator instance;

    private File cacheDir;
    private File mediaDir;

    private Retrofit reviewerBase;

    private ReviewerApiBase reviewerApi;
    private ShoppingApiBase shoppingApi;

    private NetworkRepository networkRepo;

    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private ReportRepository reportRepository;

    private ResourceRepository resourceRepository;
    private VkApiBase vkApiBase;

    ServiceLocator(){}

    public static ServiceLocator getInstance() {
        if (instance == null){
            instance = new ServiceLocator();
        }
        return instance;
    }

    public void init(Application app){
        ReviewerRoomDb db = ReviewerRoomDb.getDatabase(app);

        reviewRepository = new ReviewRepositoryImpl(db.reviewDAO());
        userRepository = new UserRepositoryImpl(db.userDao());
        reportRepository = new ReportRepositoryImpl(db.reportDao());

        reviewerBase = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://reviwer-api.onrender.com")
                .build();


        reviewerApi = new ReviewerApiBase();
        vkApiBase = new VkApiBase();
        cacheDir = app.getCacheDir();
        mediaDir = app.getExternalMediaDirs()[0];
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public File getMediaDir() {
        return mediaDir;
    }

    public ResourceRepository getResourceRepository() {
        if (resourceRepository == null){
            resourceRepository = new ResourceRepository();
        }
        return resourceRepository;
    }

    public ReportRepository getReportRepository(){
        return reportRepository;
    }

    public NetworkRepository getNetworkRepo(){
        if (networkRepo == null){
            networkRepo = new NetworkRepository();
        }
        return networkRepo;
    }

    public UserRepository getUserRepository(){
        return userRepository;
    }

    public ReviewerApiBase getReviewerApi() {
        if (reviewerApi == null){
            reviewerApi = new ReviewerApiBase();
        }
        return reviewerApi;
    }

    public ShoppingApiBase getShoppingApi(){
        if (shoppingApi == null){
            shoppingApi = new ShoppingApiBase();
        }
        return shoppingApi;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public VkApiBase getVkApiBase() {
        return vkApiBase;
    }
}
