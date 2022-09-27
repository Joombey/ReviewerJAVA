package com.example.reviewerjava.ui.view;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.reviewerjava.data.model.Author;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.model.Shop;
import com.example.reviewerjava.databinding.AddReviewFragmentBinding;
import com.example.reviewerjava.ui.viewmodel.ReviewListViewModel;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class AddReviewFragment extends Fragment {
    AddReviewFragmentBinding mBinding;
    ReviewListViewModel mViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = AddReviewFragmentBinding.inflate(inflater, container, false);

        mBinding.confirmButton.setOnClickListener((View v) -> {
            List<Shop> shopList = new ArrayList<>();
            List<String> cities = new ArrayList<>();
            cities.add("Moscow");
            cities.add("Belgorod");
            cities.add("Vladimir");
            cities.add("Khimki");
            Formatter formatter = new Formatter();
            Calendar calendar = Calendar.getInstance();
            Shop shop = new Shop("BBNS", cities);
            shopList.add(shop);
            Review review = new Review(
                    mBinding.titleEdit.getText().toString(),
                    mBinding.textEdit.getText().toString(),
                    (formatter.format("%d.%d.%d",
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.YEAR))).toString(),
                    new Author("ADMIN", "Moscow"),
                    "AdminPicture",
                    new Item(
                            "SO HARD CPU",
                            shopList
                    ));
            mViewModel.createReview(review);
            getActivity().getSupportFragmentManager().popBackStack();
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBinding = null;
        mViewModel = null;
    }
}
