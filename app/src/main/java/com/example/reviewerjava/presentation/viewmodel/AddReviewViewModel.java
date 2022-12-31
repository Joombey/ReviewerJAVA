package com.example.reviewerjava.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.di.ServiceLocator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddReviewViewModel extends ViewModel {
    public void addReview(Review review){
//        RepositoryController.addReview(ReviewEntity.getInstance(review));
        ServiceLocator.getInstance().getReviewerApi().createNewReview(ReviewEntity.getInstance(review));
    }
    public LiveData<List<Item>> getItemsByRequest(String query, File cacheDir){
        return ServiceLocator.getInstance().getShoppingApi().getItemsByRequest(query, cacheDir);
//        return RepositoryController.getItemsByRequest(query, cacheDir);
    }

    public Item moveImageToMedia(File parent, Item item) throws IOException {
        return ServiceLocator.getInstance().getResourceRepository().moveToMedia(parent, item);
//        return RepositoryController.moveToMedia(parent, item);
    }

    public UserEntity getUser(){
        return ServiceLocator.getInstance().getUserRepository().getUserByName(CurrentUser.getInstance().getUser().getName());
//        return RepositoryController.getUserByName(RepositoryController.getCurrentUser().getName());
    }
}
