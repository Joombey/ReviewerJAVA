package com.example.reviewerjava.data.room.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;

public class ReportAndReview {
    @Embedded
    public ReportEntity report;
    @Relation(
            entity = ReviewEntity.class,
            parentColumn = "reviewId",
            entityColumn = "id"
    )
    public ReviewEntity review;
}
