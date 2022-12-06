package com.example.reviewerjava.data.retrofit.response;

import com.example.reviewerjava.data.retrofit.request.ReviewDto;
import com.example.reviewerjava.data.room.models.ReportEntity;

import java.util.List;

public class ReportsWithReviewsResponse {
    List<ReportEntity> reports;
    List<ReviewDto> reviews;

    public ReportsWithReviewsResponse(List<ReportEntity> reports, List<ReviewDto> reviews) {
        this.reports = reports;
        this.reviews = reviews;
    }

    public List<ReportEntity> getReports() {
        return reports;
    }

    public void setReports(List<ReportEntity> reports) {
        this.reports = reports;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }
}
