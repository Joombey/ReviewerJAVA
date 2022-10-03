package com.example.reviewerjava.ui.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.models.ReviewRoom;
import com.example.reviewerjava.databinding.ReviewListElementBinding;
import com.example.reviewerjava.ui.view.ReviewFragment;
import com.google.gson.Gson;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder> implements AdapterView.OnItemSelectedListener {
    private List<ReviewRoom> data;
    private MainActivity mActivity;
    public ReviewListAdapter(List<ReviewRoom> mReviewList, MainActivity activity){
        this.mActivity = activity;
        this.data = mReviewList;
    }

    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewListElementBinding binding = ReviewListElementBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReviewListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        Log.i("content1", position + "");

        ReviewRoom review = data.get(position);

        holder.binding.userName.setText(review.getAuthor().getName());
        holder.binding.creationTime.setText(review.getCreationTime());
        holder.binding.itemTitle.setText(review.getItem().getItemName());
        holder.binding.reviewListElementTitle.setText(review.getReviewTitle());

        holder.binding.reviewListElementTitle.setOnClickListener(view -> {
            Gson gson = new Gson();
            String serializedReviewRoom = gson.toJson(review, ReviewRoom.class);
            mActivity.setFragment(new ReviewFragment(), serializedReviewRoom);
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                mActivity.getApplicationContext(),
                android.R.layout.simple_spinner_item,
                review.getParagraphTitleList()
                );
        holder.binding.spinner.setAdapter(arrayAdapter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ReviewListViewHolder extends RecyclerView.ViewHolder{
        private ReviewListElementBinding binding;
        public ReviewListViewHolder(ReviewListElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mActivity.setFragment(new ReviewFragment(), i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
