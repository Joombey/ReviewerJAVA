package com.example.reviewerjava.data.retrofit.service;


import com.example.reviewerjava.data.model.Report;
import com.example.reviewerjava.data.retrofit.request.ReviewDto;
import com.example.reviewerjava.data.retrofit.request.UserRequest;
import com.example.reviewerjava.data.retrofit.request.pks.ReviewId;
import com.example.reviewerjava.data.retrofit.request.pks.UserId;
import com.example.reviewerjava.data.retrofit.response.ReportsWithReviewsResponse;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReviewerService {
    @POST("/auth/sign-in")
    Call<UserAndPermission> auth(@Body UserId userId);

    @POST("/auth/sign-up")
    Call<UserAndPermission> signUp(
            @Body UserRequest user
    );

    @Multipart
    @POST("/upload/{fileName}")
    Call<ResponseBody> uploadToServer(
            @Part MultipartBody.Part filePart,
            @Part("id") UserId userInfo
    );

    @POST("/new-review")
    Call<ReviewId> newReview(
            @Body ReviewDto review
    );

    @GET("/get-reviews-for/{user}")
    Call<List<ReviewDto>> fetchAllReviews(@Path("user") String login);

    @POST("/moderator/review-block")
    Call<ReportsWithReviewsResponse> blockReview(@Query("report_id") int report_id, @Query("moder") String moderatorName);

    @POST("/moderator/report-deny/{reportId}")
    Call<List<Report>> denyReport(@Path("reportId") int reportId, @Query("moder") String moder);

    @POST("admin/change-role/{userLogin}")
    Call<UserAndPermission> changeRole(@Path("userLogin") String userLogin, @Query("role") String role, @Query("admin") String admin);

    @POST("admin/ban/{userLogin}")
    Call<List<UserAndPermission>> banUser(@Path("userLogin") String bannedUser, @Query("admin") String admin);

    @GET("admin/user-list")
    Call<List<UserAndPermission>> getUserList(@Query("admin") String admin);

    @POST("/report")
    Call<List<Report>> report(@Query("review_id") int reviewId);

    @GET("/moderator/report-list")
    Call<List<Report>> getReportList(@Query("moder") String moderName);
}
