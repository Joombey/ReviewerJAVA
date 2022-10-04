package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.models.ReviewRoom;
import com.example.reviewerjava.databinding.ReviewFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.ParagraphListAdapter;
import com.example.reviewerjava.ui.view.adapter.ReviewListAdapter;
import com.example.reviewerjava.ui.viewmodel.ReviewListViewModel;
import com.example.reviewerjava.utils.Scroller;
import com.google.gson.Gson;

public class ReviewFragment extends Fragment implements Scroller {
    private ReviewFragmentBinding mBinding;
    private ReviewListViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ReviewFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);

        mBinding.paragraphList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.paragraphList.setAdapter(new ParagraphListAdapter(
                mViewModel.getReviewById(getArguments().getInt("reviewId")).getParagraphsList(),
                (MainActivity) getActivity(),
                null,
                false
        ));
        int val = getArguments().getInt("position", -1);
        if(val != -1) scrollTo(-1);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void scrollTo(int itemPosition) {
        mBinding.paragraphList.scrollToPosition(itemPosition);
    }

    @Override
    public void addItem(int position) {
    }
}
