package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reviewerjava.databinding.ReviewListBinding;
import com.example.reviewerjava.databinding.ReviewListFragmentBinding;

public class ReviewListFragment extends Fragment {
    private ReviewListFragmentBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = ReviewListFragmentBinding.inflate(getLayoutInflater(), container, false);
        return mBinding.getRoot();
    }
}
