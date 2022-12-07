package com.example.reviewerjava.data.model;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.stream.Collectors;

public class Report {
    @SerializedName("id")
    private int reviewId;
    @SerializedName("reportAmt")
    private int reportAmt;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReportAmt() {
        return reportAmt;
    }

    public void setReportAmt(int reportAmt) {
        this.reportAmt = reportAmt;
    }

    public static List<ReportEntity> convertToEntity(List<Report> reportList){
        return reportList.stream().map(report ->{
            return new ReportEntity(report.reviewId, report.reportAmt);
        }).collect(Collectors.toList());
    }

    public static ReportEntity convertToEntity(Report report){
        return new ReportEntity(report.reviewId, report.reportAmt);
    }
}
