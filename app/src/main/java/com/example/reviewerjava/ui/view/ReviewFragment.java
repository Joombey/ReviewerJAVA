package com.example.reviewerjava.ui.view;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.relation.ReviewAndUser;
import com.example.reviewerjava.databinding.ReviewFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.ParagraphListAdapter;
import com.example.reviewerjava.ui.viewmodel.ReviewViewModel;
import com.example.reviewerjava.utils.Scroller;

public class ReviewFragment extends Fragment implements Scroller {
    private ReviewFragmentBinding mBinding;
    private ReviewViewModel mViewModel;
    private ReviewAndUser reviewAndUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ReviewFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);

        reviewAndUser = mViewModel.getReviewAndUser(getArguments().getInt("reviewId"));
        if (reviewAndUser == null){
            ((MainActivity) getActivity()).popBackStack();
        }
        mBinding.reviewTitle.setText(reviewAndUser.review.getReviewTitle());
        mBinding.authorCity.setText(reviewAndUser.user.getCity());
        mBinding.authorName.setText(reviewAndUser.user.getName());
        mBinding.itemName.setText(reviewAndUser.review.getItem().getItemName());
        mBinding.reviewDate.setText(reviewAndUser.review.getCreationTime());
        mBinding.itemImage.setImageBitmap(
                BitmapFactory.decodeFile(
                        Uri.parse(reviewAndUser.review.getItem().getItemImage())
                                .getPath())
        );
        mBinding.reportBtn.setOnClickListener(view -> {
            mViewModel.report(reviewAndUser.review.id);
        });

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBinding.paragraphList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.paragraphList.setAdapter(
                new ParagraphListAdapter(
                        reviewAndUser.review.getRoomParagraphList(),
                        (MainActivity) getActivity(),
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
