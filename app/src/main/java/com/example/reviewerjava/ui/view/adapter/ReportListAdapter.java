package com.example.reviewerjava.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.room.relation.ReportAndReview;
import com.example.reviewerjava.databinding.BlockListElementBinding;
import com.example.reviewerjava.ui.view.ReviewFragment;

import java.util.List;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportListViewHolder> {
    BlockListElementBinding binding;
    private List<ReportAndReview> reportAndReviewList;

    public ReportListAdapter(List<ReportAndReview> reportAndReview){
        this.reportAndReviewList = reportAndReview;
    }

    @NonNull
    @Override
    public ReportListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = BlockListElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReportListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportListViewHolder holder, int position) {
        holder.binding.blockBtn.setOnClickListener(v->{

        });

        holder.binding.notBlockBtn.setOnClickListener(v->{

        });

        holder.binding.reportCounter.setText(reportAndReviewList.get(position).report.getReportAmt());
        holder.binding.reviewReportTitile.setText(reportAndReviewList.get(position).review.id);
        holder.binding.reviewField.setOnClickListener(v->{
            ((MainActivity)holder.binding.getRoot().getContext()).setFragment(
                    new ReviewFragment(),
                    reportAndReviewList.get(position).review.id
            );
        });
    }

    @Override
    public int getItemCount() {
        return reportAndReviewList.size();
    }

    class ReportListViewHolder extends RecyclerView.ViewHolder{

        BlockListElementBinding binding;
        public ReportListViewHolder(BlockListElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
