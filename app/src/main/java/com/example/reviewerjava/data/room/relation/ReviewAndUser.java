package com.example.reviewerjava.data.room.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;

public class ReviewAndUser {
    @Embedded
    public ReviewEntity review;
    @Relation(
            parentColumn = "author",
            entityColumn = "name"
    )
    public UserEntity user;

    public ReviewAndUser(ReviewEntity review, UserEntity user){
        this.review = review;
        this.user = user;
    }
}
