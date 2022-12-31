package com.example.reviewerjava.data.repository.repoimpl;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.repository.repos.ReportRepository;
import com.example.reviewerjava.data.room.ReviewerRoomDb;
import com.example.reviewerjava.data.room.daos.ReportDao;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.di.ServiceLocator;

import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {
    private ReportDao dao;

    public ReportRepositoryImpl(ReportDao dao) {
        this.dao = dao;
    }

    @Override
    public LiveData<List<ReportAndReview>> getReports() {
        return dao.getAllReports();
    }

    @Override
    public void deny(ReportEntity report) {
        dao.deleteReport(report);
    }

    @Override
    public void report(int id) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.createReport(new ReportEntity(id));
        });
    }

    @Override
    public void addReportList(List<ReportEntity> reportList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertReportList(reportList);
        });
    }

//    @Override
//    public void updateReportList() {
//        ServiceLocator.getInstance().getReviewerApi().updateReportList();
//    }
}
