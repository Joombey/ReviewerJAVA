package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.RepositoryController;

public class AddReviewViewModel extends ViewModel {
    public void addReview(Review review){
        RepositoryController.getAddReviewRepository().addReview(review);
    }
}
