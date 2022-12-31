package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.User;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.repository.RoomRepository;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
import com.example.reviewerjava.di.ServiceLocator;

public class ReviewViewModel extends ViewModel {
    public ReviewEntity getReviewById(int id){
        return ServiceLocator.getInstance().getReviewRepository().getReviewById(id);
//        return RepositoryController.getReviewById(id);
    }
    public UserEntity getUserByName(String userName){
        return ServiceLocator.getInstance().getUserRepository().getUserByName(userName);
//        return RepositoryController.getUserByName(userName);
    }

    public ReviewAndUser getReviewAndUser(int reviewId) {
        return ServiceLocator.getInstance().getReviewRepository().getReviewAndUserByReviewId(reviewId);
//        return RepositoryController.getReviewAndUser(reviewId);
    }

    public void report(int id) {
        ServiceLocator.getInstance().getReportRepository().report(id);
    }
}
