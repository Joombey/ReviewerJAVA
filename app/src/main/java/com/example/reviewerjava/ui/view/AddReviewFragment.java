package com.example.reviewerjava.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.example.reviewerjava.data.model.Shop;
import com.example.reviewerjava.data.room.models.ReviewRoom;
import com.example.reviewerjava.databinding.AddReviewFragmentBinding;
import com.example.reviewerjava.ui.view.adapter.ParagraphListAdapter;
import com.example.reviewerjava.ui.view.adapter.ReviewListAdapter;
import com.example.reviewerjava.ui.viewmodel.AddReviewViewModel;
import com.example.reviewerjava.ui.viewmodel.ReviewListViewModel;
import com.example.reviewerjava.utils.Scroller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

public class AddReviewFragment extends Fragment implements Scroller{
    AddReviewFragmentBinding mBinding;
    ReviewListViewModel mReviewListViewModel;
    AddReviewViewModel mAddReviewViewModel;
    List<Paragraph> paragraphList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        paragraphList.add(new Paragraph("", "", new ArrayList<>()));
        mBinding = AddReviewFragmentBinding.inflate(inflater, container, false);
        mBinding.paragraphContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        mBinding.confirmBtn.setOnClickListener(view -> {
            List<String> cities = new ArrayList<>();
            List<Shop> shops = new ArrayList<>();
            cities.add("Moscow");
            shops.add(new Shop(mBinding.shopTitle.getText().toString(), cities));

            /*List<Paragraph> paragraphs = ((ParagraphListAdapter) mBinding.paragraphContainer.getAdapter()).getData();
            for(int i = 0; i < paragraphs.size(); i++){
                Log.i("content14", paragraphs.get(i).getParagraphTitle() + "\n" + paragraphs.get(i).getParagraphText());
            }*/

            mAddReviewViewModel.addReview(new Review(
                    mBinding.titleEdit.getText().toString(),
                    paragraphList,
                    new Formatter().format("%d.%d.%d",
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.YEAR)).toString(),
                    new Author("Admin", "Moscow", ""),
                    new Item(mBinding.itemName.getText().toString(), shops)
            ));
            ((MainActivity)getActivity()).popBackStack();
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
    public void addItem(int position) {
        paragraphList.add(position, new Paragraph(
                "",
                "",
                new ArrayList<>()
        ));
        mBinding.paragraphContainer.getAdapter().notifyItemInserted(position);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBinding = null;
        mAddReviewViewModel = null;
    }
}
