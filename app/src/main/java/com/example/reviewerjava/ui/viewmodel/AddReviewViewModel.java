package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.roomModels.ReviewRoom;

public class AddReviewViewModel extends ViewModel {
    public void addReview(Review review){
        RepositoryController.getAddReviewRepository().addReview(ReviewRoom.getInstance(review));
    }
}
