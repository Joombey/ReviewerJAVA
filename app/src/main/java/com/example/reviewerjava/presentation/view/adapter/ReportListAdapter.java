package com.example.reviewerjava.presentation.view.adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.databinding.ReportElementBinding;
import com.example.reviewerjava.presentation.view.ReviewFragment;
import com.example.reviewerjava.utils.Reporter;
import com.google.gson.Gson;

import java.util.List;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportListViewHolder> {
    private final Reporter reporter;
    ReportElementBinding binding;
    private final List<ReportAndReview> reportAndReviewList;

    public ReportListAdapter(List<ReportAndReview> reportAndReview, Reporter reporter){
        this.reportAndReviewList = reportAndReview;
        this.reporter = reporter;
    }

    @NonNull
    @Override
    public ReportListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ReportElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReportListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportListViewHolder holder, int position) {
        Log.i("DATA123", new Gson().toJson(reportAndReviewList.get(position).review, ReviewEntity.class));
        holder.binding.review.setOnClickListener(v->{
            ((MainActivity)holder.binding.getRoot().getContext()).setFragment(
                    new ReviewFragment(), reportAndReviewList.get(position).review.id
            );
        });

        holder.binding.deny.setOnClickListener(v->{
            reporter.deny(reportAndReviewList.get(position).report);
        });
        holder.binding.reportAmt.setText(reportAndReviewList.get(position).report.reportAmt + "");
        holder.binding.reviewTitle.setText(reportAndReviewList.get(position).review.reviewTitle + "");
        holder.binding.itemImage.setImageBitmap(
                    BitmapFactory.decodeFile(
                            Uri.parse(
                                    reportAndReviewList.get(position).review.getItem().getItemImage())
                                    .getPath()
                    )
        );
        holder.binding.ban.setOnClickListener(v->{
            reporter.ban(reportAndReviewList.get(position).review);
        });
    }

    @Override
    public int getItemCount() {
        return reportAndReviewList.size();
    }

    class ReportListViewHolder extends RecyclerView.ViewHolder{
        ReportElementBinding binding;
        public ReportListViewHolder(ReportElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
