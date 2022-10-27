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

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.model.Author;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Paragraph;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.databinding.AddReviewFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.ItemListAdapter;
import com.example.reviewerjava.ui.view.adapter.ParagraphListAdapter;
import com.example.reviewerjava.ui.viewmodel.AddReviewViewModel;
import com.example.reviewerjava.ui.viewmodel.ReviewListViewModel;
import com.example.reviewerjava.utils.ItemSetter;
import com.example.reviewerjava.utils.Scroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

public class AddReviewFragment extends Fragment implements Scroller, ItemSetter {
    AddReviewFragmentBinding mBinding;
    ReviewListViewModel mReviewListViewModel;
    AddReviewViewModel mAddReviewViewModel;
    Item item;
    List<Paragraph> paragraphList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        paragraphList.add(new Paragraph("", "", new ArrayList<>()));


        mBinding = AddReviewFragmentBinding.inflate(inflater, container, false);
        mBinding.itemName.setOnFocusChangeListener((view, b)-> {
            if(!b){
                mBinding.itemSpinner.setVisibility(View.GONE);
            } else mBinding.itemSpinner.setVisibility(View.VISIBLE);
        });
        mBinding.paragraphContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.itemSpinner.setLayoutManager(new LinearLayoutManager(getContext()));

        mBinding.confirmBtn.setOnClickListener(view -> {
            List<String> shops = new ArrayList<>();
            shops.add(mBinding.shopTitle.getText().toString());
            try {
                item = mAddReviewViewModel.moveImageToMedia(getContext().getExternalMediaDirs()[0], item);
                mAddReviewViewModel.addReview(new Review(
                        mBinding.titleEdit.getText().toString(),
                        paragraphList,
                        new Formatter().format("%d.%d.%d",
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                                Calendar.getInstance().get(Calendar.MONTH),
                                Calendar.getInstance().get(Calendar.YEAR)).toString(),
                        new Author("Admin", "Moscow", ""),
                        item
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ((MainActivity)getActivity()).popBackStack();
        });

        mBinding.searchButton.setOnClickListener(view -> {
            String queriedText = mBinding.itemName.getText().toString();
            if (!queriedText.equals("")) {
                mAddReviewViewModel.getItemsByRequest(queriedText, getContext().getCacheDir())
                        .observe(getViewLifecycleOwner(), list -> {
                            mBinding.itemSpinner.setVisibility(View.VISIBLE);
                            mBinding.itemSpinner.setAdapter(
                                    new ItemListAdapter(this, list, getContext())
                            );
                        });
            }else if(mBinding.itemSpinner.getVisibility() != View.GONE) mBinding.itemSpinner.setVisibility(View.GONE);
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAddReviewViewModel = new ViewModelProvider(this).get(AddReviewViewModel.class);
        mReviewListViewModel = new ViewModelProvider(this).get(ReviewListViewModel.class);
        mBinding.paragraphContainer.setAdapter(new ParagraphListAdapter(
                paragraphList,
                (MainActivity) getActivity(),
                this,
                true
        ));
    }

    @Override
    public void scrollTo(int itemPosition) {
        mBinding.paragraphContainer.scrollToPosition(itemPosition);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mBinding = null;
        mAddReviewViewModel = null;
    }

    @Override
    public void setItem(Item newItem) {
        item = newItem;
        mBinding.itemName.setText(item.getItemName());
        mBinding.itemSpinner.setVisibility(View.GONE);
    }
}
