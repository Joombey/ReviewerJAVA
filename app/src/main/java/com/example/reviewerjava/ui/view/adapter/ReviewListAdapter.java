package com.example.reviewerjava.ui.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>{
    @NonNull
    @Override
    public ReviewListAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListAdapter.ReviewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
