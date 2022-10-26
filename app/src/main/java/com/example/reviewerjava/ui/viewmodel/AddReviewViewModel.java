package com.example.reviewerjava.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.room.models.ReviewRoom;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddReviewViewModel extends ViewModel {
    public void addReview(Review review){
        RepositoryController.getAddReviewRepository().addReview(ReviewRoom.getInstance(review));
    }
    public LiveData<List<Item>> getItemsByRequest(String query, File cacheDir){
        return RepositoryController.getShoppingQuery().getItemsByRequest(query, cacheDir);
    }

    public Item moveFromCacheChosenItem(File parent, Item item) throws IOException {
        return RepositoryController.moveToMedia(parent, item);
    }
}
