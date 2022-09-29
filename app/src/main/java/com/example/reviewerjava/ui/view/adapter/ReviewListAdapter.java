package com.example.reviewerjava.ui.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.data.room.reviewDTO.ReviewDTO;
import com.example.reviewerjava.data.room.roomModels.ReviewRoom;
import com.example.reviewerjava.databinding.ReviewListBinding;
import com.example.reviewerjava.ui.view.ReviewFragment;
import com.google.gson.Gson;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder>{
    private List<ReviewRoom> mData;
    private MainActivity mActivity;
    public ReviewListAdapter(List<ReviewRoom> mReviewList, MainActivity activity){
        this.mActivity = activity;
        this.mData = mReviewList;
    }

    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewListBinding binding = ReviewListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReviewListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        ReviewRoom review = mData.get(position);
        holder.binding.userName.setText(review.getAuthor().getName());

        holder.binding.reviewText.setText(review.getTitle());
        holder.binding.reviewText.setOnClickListener(view -> {
            Gson gson = new Gson();
            String serializedReviewRoom = gson.toJson(review, ReviewRoom.class);
            mActivity.setFragment(new ReviewFragment(), serializedReviewRoom);
        });
        holder.binding.creationTime.setText(review.getCreationTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ReviewListViewHolder extends RecyclerView.ViewHolder{
        private ReviewListBinding binding;
        public ReviewListViewHolder(ReviewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
