package com.example.reviewerjava.data.retrofit.base;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;

import com.example.reviewerjava.data.retrofit.request.ReviewDto;
import com.example.reviewerjava.data.retrofit.request.UserRequest;

import com.example.reviewerjava.data.retrofit.request.pks.ReviewId;
import com.example.reviewerjava.data.retrofit.request.pks.UserId;
import com.example.reviewerjava.data.retrofit.response.ReportsWithReviewsResponse;
import com.example.reviewerjava.data.retrofit.service.ReviewerService;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.example.reviewerjava.di.ServiceLocator;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewerApiBase {
    private static boolean result;
    private ReviewerService api;
    public ReviewerApiBase(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.173:8080")
                .build();

        api = retrofit.create(ReviewerService.class);
    }

    public boolean signIn(String login, String password) {
        result = false;
        api.auth(new UserId(login, password)).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if(response.isSuccessful()) {
//                    RepositoryController.addToLocalIfRequired(((UserEntity) new Object()));
                    RepositoryController.addUserAndPermission(response.body());
                    result = true;
                }
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result;
    }


    public boolean signUp(UserRequest user) {
        MutableLiveData<Boolean> result = new MutableLiveData<>(false);
        api.signUp(user).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if(response.isSuccessful()){
                    try {
                        Log.i("REPSPONSE", response.body().getPermission().role);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    RepositoryController.addUserAndPermission(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result.getValue();
    }




    public boolean uploadFileToServer(File file, UserId user){

        MutableLiveData<Boolean> result = new MutableLiveData<>(false);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData(
                "file",
                file.getName(),
                RequestBody.create(MediaType.parse("image/*"), file)
        );

        api.uploadToServer(
                filePart,
                user
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                     result.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
            }
        });
        return result.getValue();
    }

    public void createNewReview(ReviewEntity review){
        ReviewDto request = new ReviewDto(review);
        api.newReview(request).enqueue(new Callback<ReviewId>() {
            @Override
            public void onResponse(Call<ReviewId> call, Response<ReviewId> response) {
                if (response.isSuccessful()){
                    review.id = response.body().getId();
                    ServiceLocator.getInstance().getLocalBase().addReview(review);
                }
            }

            @Override
            public void onFailure(Call<ReviewId> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fetchAllReview(){
        String userLogin = CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
        if(userLogin == null){
            userLogin = "";
        }
        api.fetchAllReviews(userLogin).enqueue(new Callback<List<ReviewDto>>() {
            @Override
            public void onResponse(Call<List<ReviewDto>> call, Response<List<ReviewDto>> response) {
                if (response.isSuccessful()){
                    ServiceLocator.getInstance().getLocalBase().saveAllReviews(
                            response.body().stream().map(reviewDto ->
                                new ReviewEntity(
                                reviewDto.getId().getId(),
                                reviewDto.getId().getAuthor(),
                                reviewDto.getItem(),
                                reviewDto.getParagraphs()
                            )).collect(Collectors.toList()));
                }
            }

            @Override
            public void onFailure(Call<List<ReviewDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fetchAll(){
        api.fetchAll().enqueue(new Callback<List<ReviewDto>>() {
            @Override
            public void onResponse(Call<List<ReviewDto>> call, Response<List<ReviewDto>> response) {
                response.body().stream();
            }

            @Override
            public void onFailure(Call<List<ReviewDto>> call, Throwable t) {

            }
        });
    }

    public void banUser(String userName){
        String currentUserName = CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
        api.banUser(userName, currentUserName).enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if (response.isSuccessful()){
                    ServiceLocator.getInstance().getLocalBase().addUserList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void changeRole(String userName, String newRole){
        String currentUserName = CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
        api.changeRole(userName, newRole, currentUserName).enqueue(new Callback<UserAndPermission>() {
            @Override
            public void onResponse(Call<UserAndPermission> call, Response<UserAndPermission> response) {
                if (response.isSuccessful()) {
                    ServiceLocator.getInstance().getLocalBase().addUserAndPermission(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserAndPermission> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getUserList(){
        String currentUserName = CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
        api.getUserList(currentUserName).enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if (response.isSuccessful()) {
                    ServiceLocator.getInstance().getLocalBase().addUserList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getReportList(){
        String currentUserName = CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
        api.getReportList(currentUserName).enqueue(new Callback<List<ReportEntity>>() {
            @Override
            public void onResponse(Call<List<ReportEntity>> call, Response<List<ReportEntity>> response) {
                if(response.isSuccessful()){
                    ServiceLocator.getInstance().getLocalBase().addReportList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ReportEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void denyReport(int reportId){
        String currentUserName = CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
        api.denyReport(reportId, currentUserName).enqueue(new Callback<List<ReportEntity>>() {
            @Override
            public void onResponse(Call<List<ReportEntity>> call, Response<List<ReportEntity>> response) {
                if(response.isSuccessful()){
                    ServiceLocator.getInstance().getLocalBase().addReportList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ReportEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void report(int reviewId){
        api.report(reviewId).enqueue(new Callback<List<ReportEntity>>() {
            @Override
            public void onResponse(Call<List<ReportEntity>> call, Response<List<ReportEntity>> response) {
                if (response.isSuccessful()){
                    ServiceLocator.getInstance().getLocalBase().addReportList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ReportEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void blockReview(int reportId){
        String currentUserName = CurrentUser.getInstance().getUserAndPermission().getValue().user.getName();
        api.blockReview(reportId, currentUserName).enqueue(new Callback<ReportsWithReviewsResponse>() {
            @Override
            public void onResponse(Call<ReportsWithReviewsResponse> call, Response<ReportsWithReviewsResponse> response) {
                if (response.isSuccessful()){
                    List<ReportEntity> reportList = response.body().getReports();
                    List<ReviewEntity> reviewList = response.body()
                            .getReviews()
                            .stream()
                            .map(reviewDto -> ReviewDto.convertToEntity(reviewDto))
                            .collect(Collectors.toList());
                    ServiceLocator.getInstance().getLocalBase().addReportsWithReviews(reportList, reviewList);
                }
            }

            @Override
            public void onFailure(Call<ReportsWithReviewsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
