package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.RepositoryController;

import java.util.List;

public class ReviewListViewModel extends ViewModel {
    public MutableLiveData<List<Review>> getReviews(){
        return RepositoryController.getRepository().getReviewList();
    }
}
