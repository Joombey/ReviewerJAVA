package com.example.reviewerjava.ui.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.R;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.databinding.ReviewListElementBinding;
import com.example.reviewerjava.ui.view.ReviewFragment;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder> {
    private List<ReviewEntity> data;
    private MainActivity mActivity;
    public ReviewListAdapter(List<ReviewEntity> mReviewList, MainActivity activity){
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
        ReviewEntity review = data.get(position);

        holder.binding.shareBtn.setOnClickListener(v-> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://rakhm.f.e_reviewer_java/" + review.id);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            mActivity.startActivity(shareIntent);
        });

        holder.binding.userName.setText(review.getAuthor().getName());
        holder.binding.creationTime.setText(review.getCreationTime());
        holder.binding.itemTitle.setText(review.getItem().getItemName());
        holder.binding.reviewListElementTitle.setText(review.getReviewTitle());

        holder.binding.reviewContainer.setOnClickListener(view -> {
            mActivity.setFragment(new ReviewFragment(), review.id);
        });
        holder.binding.showParagraphListBtn.setOnClickListener(v->{
            if(holder.binding.paragraphListContainer.getVisibility() == View.GONE){
                holder.binding.showParagraphListBtn.setImageResource(R.drawable.ic_down);
                holder.binding.paragraphListContainer.setVisibility(View.VISIBLE);
            } else{
                holder.binding.showParagraphListBtn.setImageResource(R.drawable.ic_up_big);
                holder.binding.paragraphListContainer.setVisibility(View.GONE);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        holder.binding.paragraphListContainer.setLayoutManager(linearLayoutManager);
        holder.binding.paragraphListContainer.setAdapter(
                new ParagraphSpinner(
                        mActivity,
                        review.getParagraphTitleList(),
                        review.id
                )
        );
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
}
