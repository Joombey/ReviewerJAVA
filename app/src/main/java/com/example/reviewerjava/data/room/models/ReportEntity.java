package com.example.reviewerjava.data.room.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
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
    @PrimaryKey
    public int id;
    public int reportAmt;
}
