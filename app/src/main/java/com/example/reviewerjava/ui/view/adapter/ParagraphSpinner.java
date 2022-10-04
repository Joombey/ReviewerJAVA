package com.example.reviewerjava.ui.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.databinding.SpinnerElementBinding;
import com.example.reviewerjava.ui.view.ReviewFragment;

import java.util.List;

public class ParagraphSpinner extends RecyclerView.Adapter<ParagraphSpinner.ParagrapSpinnerViewHolder> {

    private MainActivity activity;
    private List<String> paragraphTitleList;
    private int reviewId;

    public ParagraphSpinner(MainActivity activity, List<String> paragraphTitleList, int reviewId){
        this.activity = activity;
        this.paragraphTitleList = paragraphTitleList;
        this.reviewId = reviewId;
    }

    @NonNull
    @Override
    public ParagraphSpinner.ParagrapSpinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpinnerElementBinding binding = SpinnerElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ParagrapSpinnerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParagraphSpinner.ParagrapSpinnerViewHolder holder, int position) {
        holder.binding.spinnerElement.setText((position + 1) + ". " + paragraphTitleList.get(position));
        holder.binding.spinnerElement.setOnClickListener(v->{
            activity.setFragment(new ReviewFragment(), reviewId, position);
        });
    }

    @Override
    public int getItemCount() {
        return paragraphTitleList.size();
    }

    public class ParagrapSpinnerViewHolder extends RecyclerView.ViewHolder{

        private SpinnerElementBinding binding;

        public ParagrapSpinnerViewHolder(SpinnerElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
