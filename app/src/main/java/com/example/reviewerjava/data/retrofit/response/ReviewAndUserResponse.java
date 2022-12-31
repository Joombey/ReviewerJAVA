package com.example.reviewerjava.data.retrofit.response;

import com.example.reviewerjava.data.retrofit.request.ReviewDto;
import com.example.reviewerjava.data.room.relation.UserAndPermission;
import com.google.gson.annotations.SerializedName;

public class ReviewAndUserResponse {
    private ReviewDto reviewDto;
    @SerializedName("user")
    private UserAndPermission userAndPermission;

    public ReviewAndUserResponse(ReviewDto reviewDto, UserAndPermission userAndPermission) {
        this.reviewDto = reviewDto;
        this.userAndPermission = userAndPermission;
    }

    public ReviewDto getReviewDto() {
        return reviewDto;
    }

    public void setReviewDto(ReviewDto reviewDto) {
        this.reviewDto = reviewDto;
    }

    public UserAndPermission getUserAndPermission() {
        return userAndPermission;
    }

    public void setUserAndPermission(UserAndPermission userAndPermission) {
        this.userAndPermission = userAndPermission;
    }
}
