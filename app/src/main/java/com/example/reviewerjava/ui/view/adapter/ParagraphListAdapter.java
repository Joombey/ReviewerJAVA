package com.example.reviewerjava.ui.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.model.Paragraph;
import com.example.reviewerjava.databinding.AddParagraphElementBinding;
import com.example.reviewerjava.utils.Scroller;

import java.util.List;

public class ParagraphListAdapter extends RecyclerView.Adapter<ParagraphListAdapter.ParagraphListViewHolder> {

    private MainActivity activity;
    private List<Paragraph> paragraphList;
    private Scroller scroller;

    public ParagraphListAdapter(List<Paragraph> paragraphList, MainActivity activity, Scroller scroller){
        this.paragraphList = paragraphList;
        this.activity = activity;
        this.scroller = scroller;
    }

    @NonNull
    @Override
    public ParagraphListAdapter.ParagraphListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AddParagraphElementBinding binding = AddParagraphElementBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ParagraphListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParagraphListAdapter.ParagraphListViewHolder holder, int position) {
        holder.binding.paragraphTitle.setText(paragraphList.get(position).getParagraphTitle());
        holder.binding.paragraphText.setText(paragraphList.get(position).getParagraphText());
        holder.binding.imageSlider
                .setAdapter(
                        new ImageSliderAdapter(
                                paragraphList.get(position).getImages(), true, activity
                        )
                );
        holder.binding.paragraphText.setOnFocusChangeListener((view, b) -> {
            paragraphList.get(position).setParagraphText(holder.binding.paragraphText.getText().toString());

        });
        holder.binding.paragraphTitle.setOnFocusChangeListener((v, b) ->{
            paragraphList.get(position).setParagraphTitle(holder.binding.paragraphTitle.getText().toString());
        });
        holder.binding.nextParagraphBtn.setOnClickListener(v->{
            paragraphList.get(position).setParagraphTitle(holder.binding.paragraphTitle.getText().toString());
            paragraphList.get(position).setParagraphText(holder.binding.paragraphText.getText().toString());
            scroller.addItem(position + 1);
        });
    }

    @Override
    public int getItemCount() {
        return paragraphList.size();
    }

    public List<Paragraph> getData(){
        return paragraphList;
    }

    class ParagraphListViewHolder extends RecyclerView.ViewHolder {
        private AddParagraphElementBinding binding;
        public ParagraphListViewHolder(AddParagraphElementBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
