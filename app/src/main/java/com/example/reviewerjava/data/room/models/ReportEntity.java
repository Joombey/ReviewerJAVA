package com.example.reviewerjava.data.room.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Report;

@Entity(
        tableName = "reports",
        foreignKeys = @ForeignKey(
                entity = ReviewEntity.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = ForeignKey.CASCADE
        )
)
public class ReportEntity extends Report {
    public ReportEntity(int id){
        this.id = id;
        reportAmt = 1;
    }

    @Ignore
    public ReportEntity(int id, int reportAmt) {
        this.id = id;
        this.reportAmt = reportAmt;
    }

    @PrimaryKey
    public int id;
    public int reportAmt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getReportAmt() {
        return reportAmt;
    }

    @Override
    public void setReportAmt(int reportAmt) {
        this.reportAmt = reportAmt;
    }
}
