package com.example.reviewerjava.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewRoom;

import java.util.List;

public class AddReviewViewModel extends ViewModel {
    public void addReview(Review review){
        RepositoryController.getAddReviewRepository().addReview(ReviewRoom.getInstance(review));
    }
    public List<Item> getItemsByRequest(String quary){
        return RepositoryController.getShoppingQuery().getItemsByRequest(quary);
    }
}
