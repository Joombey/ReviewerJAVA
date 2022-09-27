package com.example.reviewerjava.ui.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.model.Review;
import com.example.reviewerjava.databinding.ReviewListBinding;
import com.example.reviewerjava.databinding.ReviewListFragmentBinding;
import com.example.reviewerjava.ui.view.ReviewListFragment;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder>{
    private List<Review> data;
    private MainActivity activity;
    public ReviewListAdapter(List<Review> mReviewList, MainActivity activity){
        Log.i("here", "here");
        this.activity = activity;
        this.data = mReviewList;
    }

    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewListBinding binding = ReviewListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReviewListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        Review review = data.get(position);
        holder.binding.userName.setOnClickListener(view -> {
            MainActivity mainActivity = this.activity;
            mainActivity.remove(mainActivity
                    .getSupportFragmentManager()
                    .findFragmentById(mainActivity
                            .getBinding()
                            .fragmentContainerView.getId()));
        ;});
        holder.binding.userName.setText(review.getAuthor().getName());
        holder.binding.reviewText.setText(review.getTitle());
        holder.binding.creationTime.setText(review.getCreationTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ReviewListViewHolder extends RecyclerView.ViewHolder{
        private ReviewListBinding binding;
        public ReviewListViewHolder(ReviewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
