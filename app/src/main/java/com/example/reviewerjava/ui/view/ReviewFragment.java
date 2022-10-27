package com.example.reviewerjava.ui.view;

import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.example.reviewerjava.ui.viewmodel.ReviewListViewModel;
import com.example.reviewerjava.utils.Scroller;

public class ReviewFragment extends Fragment implements Scroller {
    private ReviewFragmentBinding mBinding;
    private ReviewListViewModel mViewModel;
    private ReviewRoom reviewRoom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ReviewFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);
        reviewRoom = mViewModel.getReviewById(getArguments().getInt("reviewId"));

        mBinding.reviewTitle.setText(reviewRoom.getReviewTitle());
        mBinding.authorCity.setText(reviewRoom.getAuthor().getCity());
        mBinding.authorName.setText(reviewRoom.getAuthor().getName());
        mBinding.itemName.setText(reviewRoom.getItem().getItemName());
        mBinding.reviewDate.setText(reviewRoom.getCreationTime());
        Log.i("CONTENT", reviewRoom.getItem().getItemImage().toString());
        mBinding.itemImage.setImageBitmap(BitmapFactory.decodeFile(Uri.parse(reviewRoom.getItem().getItemImage()).getPath()));

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);
        mBinding.paragraphList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.paragraphList.setAdapter(
                new ParagraphListAdapter(
                        reviewRoom.getRoomParagraphList(),
                        (MainActivity) getActivity(),
                        null,
                        false
                )
        );
        int position = getArguments().getInt("position", -1);
        if(position != -1) scrollTo(position);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void scrollTo(int itemPosition) {
        mBinding.paragraphList.scrollToPosition(itemPosition);
    }
}
