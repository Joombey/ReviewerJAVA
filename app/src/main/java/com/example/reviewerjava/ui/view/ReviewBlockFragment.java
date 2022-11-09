package com.example.reviewerjava.ui.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reviewerjava.R;
import com.example.reviewerjava.databinding.FragmentReviewBlockBinding;
import com.example.reviewerjava.ui.view.adapter.ReportListAdapter;
import com.example.reviewerjava.ui.viewmodel.ReviewBlockViewModel;

public class ReviewBlockFragment extends Fragment {

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
        mBinding.moderatorList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getReportList().observe(getViewLifecycleOwner(), list->{
            mBinding.moderatorList.setAdapter(new ReportListAdapter(list));
        });
    }
}