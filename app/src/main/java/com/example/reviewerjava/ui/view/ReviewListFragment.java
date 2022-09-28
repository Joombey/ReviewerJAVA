package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.reviewDTO.ReviewDTO;
import com.example.reviewerjava.databinding.ReviewListFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.ReviewListAdapter;
import com.example.reviewerjava.ui.viewmodel.RegisterViewModel;
import com.example.reviewerjava.ui.viewmodel.ReviewListViewModel;

public class ReviewListFragment extends Fragment {
    private RegisterViewModel mRegisterViewModel;
    private ReviewListFragmentBinding mBinding;
    private ReviewListViewModel mReviewListViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = ReviewListFragmentBinding.inflate(inflater, container, false);
        mBinding.reviewList.setLayoutManager(new LinearLayoutManager(getContext()));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mReviewListViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);
        //Log.i("onCreate", mReviewListViewModel.getReviews().getValue().size() + "");
        mReviewListViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            mBinding.reviewList.setAdapter(new ReviewListAdapter(reviews, (MainActivity) getActivity()));
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBinding = null;
        mReviewListViewModel = null;
    }
}
