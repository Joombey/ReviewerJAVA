package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.databinding.ReviewFragmentBinding;
import com.google.gson.Gson;

public class ReviewFragment extends Fragment {
    private ReviewFragmentBinding mBinding;
    private Review mReview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ReviewFragmentBinding.inflate(inflater, container, false);
        Gson gson = new Gson();
        mReview = gson.fromJson(getArguments().getString("key"), Review.class);

        mBinding.author.setText(mReview.getAuthor().getName());
        mBinding.reviewTitle.setText(mReview.getTitle());
        mBinding.date.setText(mReview.getCreationTime());
        mBinding.reviewText.setText(mReview.getText());

        return mBinding.getRoot();
    }
}
