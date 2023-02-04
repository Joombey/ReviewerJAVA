package com.example.reviewerjava.data.retrofit.base;


import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.Report;

import com.example.reviewerjava.data.retrofit.request.FileToSend;
import com.example.reviewerjava.data.retrofit.request.ReviewDto;
import com.example.reviewerjava.data.retrofit.request.UserRequest;

import com.example.reviewerjava.data.retrofit.request.pks.ReviewId;
import com.example.reviewerjava.data.retrofit.request.pks.UserId;
import com.example.reviewerjava.data.retrofit.response.ReportsWithReviewsResponse;
import com.example.reviewerjava.data.retrofit.response.ReviewAndUserResponse;
import com.example.reviewerjava.data.retrofit.service.ReviewerService;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.example.reviewerjava.di.ServiceLocator;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

public class ReviewerApiBase {
    private final ReviewerService api;
    public ReviewerApiBase(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.173:8080")
                .build();

        api = retrofit.create(ReviewerService.class);
    }

    public LiveData<Boolean> signIn(String login, String password) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        api.auth(new UserId(login, password)).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if(response.isSuccessful()) {
                    ServiceLocator.getInstance().getUserRepository().addUserAndPermission(response.body());
                    CurrentUser.getInstance().setUserAndPermission(response.body());
                    result.setValue(true);
                }
                else result.setValue(false);
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
                result.setValue(false);
            }
        });
        return result;
    }


    public LiveData<Boolean> signUp(String login, String password, String city, String avatar) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        UserRequest user = new UserRequest(new UserId(login, password), city, avatar);
        api.signUp(user).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if(response.isSuccessful()){
                    ServiceLocator.getInstance().getUserRepository().addUserAndPermission(response.body());
                    CurrentUser.getInstance().setUserAndPermission(response.body());
                    result.setValue(true);
                }
                result.setValue(false);
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
                result.setValue(false);
            }
        });
        return result;
    }

    public void createNewReview(ReviewEntity review){
        ReviewDto request = new ReviewDto(review);
        api.newReview(request).enqueue(new Callback<ReviewId>() {
            @Override
            public void onResponse(Call<ReviewId> call, Response<ReviewId> response) {
                if (response.isSuccessful()){
                    request.setId(response.body());
                    ServiceLocator.getInstance().getReviewRepository().addReview(new ReviewEntity(request));
                }
            }

            @Override
            public void onFailure(Call<ReviewId> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fetchAllReviewFor(String userName){
        if(userName == null){
            userName = "";
        }
        api.fetchAllReviewsFor(userName).enqueue(new Callback<List<ReviewDto>>() {
            @Override
            public void onResponse(Call<List<ReviewDto>> call, Response<List<ReviewDto>> response) {
                if (response.isSuccessful()){
                    List<ReviewEntity> reviewList = response.body().stream().map(
                            reviewDto -> new ReviewEntity(reviewDto)
                    ).collect(Collectors.toList());
                    ServiceLocator.getInstance().getReviewRepository().addReviewList(reviewList);
                }
            }

            @Override
            public void onFailure(Call<List<ReviewDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fetchReviewAndUserList(){
        api.fetchReviewAndUserList().enqueue(new Callback<List<ReviewAndUserResponse>>() {
            @Override
            public void onResponse(Call<List<ReviewAndUserResponse>> call, Response<List<ReviewAndUserResponse>> response) {
                if (response.isSuccessful()) {
                    synchronized (response) {
                        List<UserAndPermission> userAndPermissionList = response.body().stream().map(
                                reviewAndUser -> new UserAndPermission(reviewAndUser.getUserAndPermission().getUser(), reviewAndUser.getUserAndPermission().permission)
                        ).collect(Collectors.toList());
                        ServiceLocator.getInstance().getUserRepository().addUserList(userAndPermissionList);
                    }
                    synchronized (response) {
                        List<ReviewEntity> reviewList = response.body().stream().map(
                                reviewAndUser -> new ReviewEntity(reviewAndUser.getReviewDto())
                        ).collect(Collectors.toList());

                        ServiceLocator.getInstance().getReviewRepository().addReviewList(reviewList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReviewAndUserResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void banUser(String userName){
        String currentUserName = CurrentUser.getInstance().getUser().getName();
        api.banUser(userName, currentUserName).enqueue(new Callback<List<UserAndPermission>>() {
            @Override
            public void onResponse(Call<List<UserAndPermission>> call, Response<List<UserAndPermission>> response) {
                if (response.isSuccessful()){
                    ServiceLocator.getInstance().getUserRepository().addUserList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserAndPermission>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void changeRole(String userName, String newRole){
        String currentUserName = CurrentUser.getInstance().getUser().getName();
        api.changeRole(userName, newRole, currentUserName).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if (response.isSuccessful()) {
                    ServiceLocator.getInstance().getUserRepository().addUserAndPermission(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getUserList(){
        String currentUserName = CurrentUser.getInstance().getUser().getName();
        api.getUserList(currentUserName).enqueue(new Callback<List<UserAndPermission>>() {
            @Override
            public void onResponse(Call<List<UserAndPermission>> call, Response<List<UserAndPermission>> response) {
                if (response.isSuccessful()) {
                    response.body().stream().map(user -> Log.i("asd", user.user.getName()));
                    ServiceLocator.getInstance().getUserRepository().addUserList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserAndPermission>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updateReportList(){
        String currentUserName = CurrentUser.getInstance().getUser().getName();
        api.getReportList(currentUserName).enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                if(response.isSuccessful()){
                    ServiceLocator.getInstance().getReportRepository().addReportList(Report.convertToEntity(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void denyReport(int reportId){
        String currentUserName = CurrentUser.getInstance().getUser().getName();
        api.denyReport(reportId, currentUserName).enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                if(response.isSuccessful()){
                    ServiceLocator.getInstance().getReportRepository().addReportList(Report.convertToEntity(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void report(int reviewId){
        api.report(reviewId).enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                if (response.isSuccessful()){
                    response.body().stream().map(report-> {
                        Log.i("DATA321", report.getReviewId() + "");
                        return report;
                    }).collect(Collectors.toList());
                    ServiceLocator.getInstance().getReportRepository().addReportList(Report.convertToEntity(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void blockReview(int reportId){
        String currentUserName = CurrentUser.getInstance().getUser().getName();
        api.blockReview(reportId, currentUserName).enqueue(new Callback<ReportsWithReviewsResponse>() {
            @Override
            public void onResponse(Call<ReportsWithReviewsResponse> call, Response<ReportsWithReviewsResponse> response) {
                if (response.isSuccessful()){
                    List<ReportEntity> reportList = Report.convertToEntity(response.body().getReports());
                    List<ReviewEntity> reviewList = response.body()
                            .getReviews()
                            .stream()
                            .map(reviewDto -> ReviewDto.convertToEntity(reviewDto))
                            .collect(Collectors.toList());
                    ServiceLocator.getInstance().getReviewRepository().addReportsWithReviews(reportList, reviewList);
                }
            }

            @Override
            public void onFailure(Call<ReportsWithReviewsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void uploadFilesToServer(){
        MultipartBody.Part filePart = MultipartBody.Part.createFormData(
                "file",
                new File("FileName").getName(),
                RequestBody.create(MediaType.parse("img/png"), new File("ASd"))
        );
        api.uploadToServer(filePart, CurrentUser.getInstance().getUser()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void sendUserPhoto(ReviewEntity review) {
        List<MultipartBody.Part> paragraphs = new ArrayList<>();
        Uri fileUri;
        FileToSend file;
        for (int i = 0; i < review.getRoomParagraphList().size(); i++) {
            for (int j = 0; j < review.getRoomParagraphList().get(i).getImages().size() - 1; j++) {
                fileUri = Uri.parse(review.getRoomParagraphList().get(i).getImages().get(j));
                file = ServiceLocator.getInstance().getResourceRepository().getFileToSendInst(fileUri);

                RequestBody requestBody = RequestBody.create(
                        MediaType.parse(fileUri.getScheme()),
                        file.getBytes()
                );

                paragraphs.add(MultipartBody.Part.createFormData(
                        "files",
                        i + " " + j + " " + file.getName(),
                        requestBody
                ));
            }
        }

        api.upload(paragraphs).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("FILES", response.code() + "");
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("FILES", "FAIL");
            }
        });
    }
}