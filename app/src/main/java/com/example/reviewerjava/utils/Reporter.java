package com.example.reviewerjava.utils;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;

public interface Reporter {
    void ban(ReviewEntity review);
    void deny(ReportEntity report);
}
