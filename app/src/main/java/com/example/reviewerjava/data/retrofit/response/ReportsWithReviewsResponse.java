package com.example.reviewerjava.data.retrofit.response;

import com.example.reviewerjava.data.model.Report;
import com.example.reviewerjava.data.retrofit.request.ReviewDto;
import com.example.reviewerjava.data.room.models.ReportEntity;

import java.util.List;

public class ReportsWithReviewsResponse {
    List<Report> reports;
    List<ReviewDto> reviews;

    public ReportsWithReviewsResponse(List<Report> reports, List<ReviewDto> reviews) {
        this.reports = reports;
        this.reviews = reviews;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }
}
