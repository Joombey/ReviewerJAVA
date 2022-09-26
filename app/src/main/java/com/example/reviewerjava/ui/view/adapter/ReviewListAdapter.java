package com.example.reviewerjava.ui.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.databinding.ReviewListBinding;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder>{
    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ReviewListViewHolder extends RecyclerView.ViewHolder{
        private ReviewListBinding binding;
        public ReviewListViewHolder(@NonNull View itemView, ReviewListBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
