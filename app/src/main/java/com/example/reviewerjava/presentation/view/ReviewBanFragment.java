package com.example.reviewerjava.presentation.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.databinding.FragmentReviewBlockBinding;
import com.example.reviewerjava.presentation.view.adapter.ReportListAdapter;
import com.example.reviewerjava.presentation.viewmodel.ReviewBlockViewModel;
import com.example.reviewerjava.utils.Reporter;

public class ReviewBanFragment extends Fragment implements Reporter {

    private FragmentReviewBlockBinding mBinding;
    private ReviewBlockViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = FragmentReviewBlockBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReviewBlockViewModel.class);
        mViewModel.updateReportList();
        mBinding.moderatorList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getReportList().observe(getViewLifecycleOwner(), list->{
            mBinding.moderatorList.setAdapter(new ReportListAdapter(list, this));
        });
    }

    @Override
    public void ban(ReviewEntity review) {
        mViewModel.ban(review);
    }

    @Override
    public void deny(ReportEntity report) {
        mViewModel.deny(report);
    }
}