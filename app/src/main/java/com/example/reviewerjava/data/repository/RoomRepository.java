package com.example.reviewerjava.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.repos.ReportRepository;
import com.example.reviewerjava.data.repository.repos.ReviewRepository;
import com.example.reviewerjava.data.repository.repos.UserRepository;
import com.example.reviewerjava.data.room.ReviewerRoomDb;
import com.example.reviewerjava.data.room.daos.ReviewDao;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

public class RoomRepository implements
        UserRepository,
        ReportRepository,
        ReviewRepository
{
    private LiveData<List<ReviewEntity>> mReviewList;
    private ReviewDao dao;

    public RoomRepository(Application application){
        ReviewerRoomDb db = ReviewerRoomDb.getDatabase(application);
        dao = db.reviewDAO();
        mReviewList = dao.getAllReviews();

//        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
//            dao.insertPermission(new Permission.Builder()
//                    .role("moder")
//                    .profileAccess(Permission.ACCESS)
//                    .reviewMakerAccess(Permission.ACCESS)
//                    .reviewBlockAccess(Permission.ACCESS)
//                    .build()
//                    .getPermissionEntityInstance());
//            dao.insertPermission(new Permission.Builder()
//                    .role("admin")
//                    .profileAccess(Permission.ACCESS)
//                    .reviewMakerAccess(Permission.ACCESS)
//                    .roleChangerAccess(Permission.ACCESS)
//                    .build()
//                    .getPermissionEntityInstance());
//            dao.insertPermission(new Permission.Builder()
//                    .role("user")
//                    .profileAccess(Permission.ACCESS)
//                    .reviewMakerAccess(Permission.ACCESS)
//                    .build()
//                    .getPermissionEntityInstance());
//            dao.insertPermission(new Permission.Builder()
//                    .role("unauthorized")
//                    .build()
//                    .getPermissionEntityInstance());
//
//            dao.insertUser(new UserEntity(UserEntity.USER, "MOSCOW", "123", UserEntity.USER));
//            dao.insertUser(new UserEntity(UserEntity.MODERATOR, "MOSCOW", "123", UserEntity.MODERATOR));
//            dao.insertUser(new UserEntity(UserEntity.ADMIN, "MOSCOW", "123", UserEntity.ADMIN));
//        });
    }

    @Override
    public void addReview(ReviewEntity review) {
        ReviewerRoomDb.databaseWriteExecutor.execute(() ->{
            dao.insertReview(review);
        });
    }

    public LiveData<List<ReviewEntity>> getReviewList() {
        return mReviewList;
    }



    @Override
    public ReviewEntity getReviewById(int id) {
        return dao.getReviewById(id);
    }

    @Override
    public LiveData<List<ReviewEntity>> getReviewsByAuthor(String userName) {
        return null;
    }

    @Override
    public ReviewAndUser getReviewAndUserByReviewId(int reviewId) {
        return dao.getReviewAndUserByReviewId(reviewId);
    }

    @Override
    public void saveAllReviews(List<ReviewEntity> reviewList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertReviewList(reviewList);
        });
    }

    public void addReportsWithReviews(List<ReportEntity> reportList, List<ReviewEntity> reviewList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertReviewList(reviewList);
            dao.insertReportList(reportList);
        });
    }

    @Override
    public LiveData<List<ReportAndReview>> getReports() {
        return dao.getAllReports();
    }

    public void ban(ReviewEntity review) {
        dao.deleteReview(review);
    }

    @Override
    public void deny(ReportEntity report) {
        dao.deleteReport(report);
    }

    @Override
    public void report(int id) {
        ReviewerRoomDb.databaseWriteExecutor.execute(() ->{
            ReportEntity report = dao.getReport(id);
            if (report == null){
                dao.createReport(new ReportEntity(id));
            } else{
                report.reportAmt += 1;
                dao.updateReport(report);
            }
        });
    }

    @Override
    public void addReportList(List<ReportEntity> reportList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(() ->{
            dao.insertReportList(reportList);
        });
    }


    @Override
    public void updateUser(UserEntity user) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()-> {
            dao.updateUserState(user);
        });
    }

    @Override
    public UserEntity getUserByName(String userName) {
        return dao.getUserByName(userName);
    }

    @Override
    public LiveData<List<UserEntity>> getUsersExcept(String userName) {
        return dao.getUsers(userName);
    }

    @Override
    public void ban(UserEntity user) {
        dao.deleteUser(user);
    }

    @Override
    public void addNewUser(UserEntity newUser) {
        dao.insertUser(newUser);
    }

    @Override
    public boolean userExists(String name) {
        if(dao.getUser(name) == null) return false;
        else return true;
    }

    @Override
    public void addUserAndPermission(UserAndPermission userAndPermission) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertUser(userAndPermission.user);
            dao.insertPermission(userAndPermission.permission);
        });
    }

    @Override
    public UserAndPermission getUserAndPermission(String name){
        UserAndPermission user = dao.getUser(name);
        if(user == null){
            return CurrentUser.getInstance().UNAUTHORIZED_USER;
        }
        return user;
    }

    @Override
    public void addUserList(List<UserAndPermission> newUserList) {
        ReviewerRoomDb.databaseWriteExecutor.execute(()->{
            dao.insertUserList(null);
        });
    }
}
