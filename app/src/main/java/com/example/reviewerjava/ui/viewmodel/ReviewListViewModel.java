package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.reviewDTO.ReviewDTO;

import java.util.List;

public class ReviewListViewModel extends ViewModel {
    public LiveData<List<Review>> getReviews(){
        return RepositoryController.getReviewListRepository().getReviewList();
    }
}
