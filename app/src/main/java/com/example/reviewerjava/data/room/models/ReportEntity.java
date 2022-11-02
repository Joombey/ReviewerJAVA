package com.example.reviewerjava.data.room.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.reviewerjava.data.model.Report;

@Entity(
        tableName = "reports",
        foreignKeys = @ForeignKey(
                entity = ReviewEntity.class,
                parentColumns = "id",
                childColumns = "reviewId"
        )
)
public class ReportEntity extends Report {
    private int id;
    private int reviewId;
    private int reportAmt;

    @Override
    public void setReviewId(int reviewId) {
        super.setReviewId(reviewId);
        this.reviewId = reviewId;
    }

    @Override
    public void setReportAmt(int reportAmt) {
        super.setReportAmt(reportAmt);
        this.reportAmt = reportAmt;
    }
}
