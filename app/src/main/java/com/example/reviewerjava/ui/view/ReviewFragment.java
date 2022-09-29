package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reviewerjava.data.room.roomModels.ReviewRoom;
import com.example.reviewerjava.databinding.ReviewFragmentBinding;
import com.google.gson.Gson;

public class ReviewFragment extends Fragment {
    private ReviewFragmentBinding mBinding;
    private ReviewRoom mReviewRoom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ReviewFragmentBinding.inflate(inflater, container, false);
        Gson gson = new Gson();
        mReviewRoom = gson.fromJson(getArguments().getString("key"), ReviewRoom.class);

        mBinding.author.setText(mReviewRoom.getAuthor().getName());
        mBinding.reviewTitle.setText(mReviewRoom.getTitle());
        mBinding.date.setText(mReviewRoom.getCreationTime());
        mBinding.reviewText.setText(mReviewRoom.getText());

        return mBinding.getRoot();
    }
}
