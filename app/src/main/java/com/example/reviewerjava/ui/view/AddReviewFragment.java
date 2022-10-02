package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.reviewerjava.data.model.Author;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Paragraph;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.model.Shop;
import com.example.reviewerjava.data.room.models.ReviewRoom;
import com.example.reviewerjava.databinding.AddReviewFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.ParagraphListAdapter;
import com.example.reviewerjava.ui.viewmodel.AddReviewViewModel;
import com.example.reviewerjava.ui.viewmodel.ReviewListViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

public class AddReviewFragment extends Fragment {
    AddReviewFragmentBinding mBinding;
    ReviewListViewModel mReviewListViewModel;
    AddReviewViewModel mAddReviewViewModel;
    List<Paragraph> paragraphList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = AddReviewFragmentBinding.inflate(inflater, container, false);
        mBinding.paragraphContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.paragraphContainer.setAdapter(new ParagraphListAdapter(paragraphList));

        mBinding.confirmBtn.setOnClickListener(view -> {
            Formatter formatter = new Formatter();
            List<String> cities = new ArrayList<>();
            List<Shop> shops = new ArrayList<>();
            shops.add(new Shop(mBinding.shopTitle.getText().toString(), cities));
            cities.add("Moscow");

            List<Paragraph> paragraphs = ((ParagraphListAdapter) mBinding.paragraphContainer.getAdapter()).getData();
            mAddReviewViewModel.addReview(ReviewRoom.getInstance(new Review(
                    mBinding.titleEdit.getText().toString(),
                    paragraphs,
                    formatter.format("%.%.%",
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.YEAR)).toString(),
                    new Author("Admin", "Moscow", ""),
                    new Item(mBinding.itemName.getText().toString(), shops)
            )));
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAddReviewViewModel = new ViewModelProvider(this).get(AddReviewViewModel.class);
        mReviewListViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBinding = null;
        mAddReviewViewModel = null;
    }
}
