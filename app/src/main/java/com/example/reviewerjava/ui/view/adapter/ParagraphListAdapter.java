package com.example.reviewerjava.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.MainActivity;
import com.example.reviewerjava.data.model.Paragraph;
import com.example.reviewerjava.databinding.AddParagraphElementBinding;

import java.util.ArrayList;
import java.util.List;

public class ParagraphListAdapter extends RecyclerView.Adapter<ParagraphListAdapter.ParagraphListViewHolder> {

    private MainActivity activity;
    private List<Paragraph> paragraphList;

    public ParagraphListAdapter(List<Paragraph> paragraphList, MainActivity activity){
        this.paragraphList = paragraphList;
        this.activity = activity;
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
        holder.binding.imageSlider
                .setAdapter(
                        new ImageSliderAdapter(
                                paragraphList.get(position).getImages(), true, activity
                        )
                );
        holder.binding.nextParagraphBtn.setOnClickListener(v->{
            paragraphList.get(position).setParagraphTitle(holder.binding.paragraphTitle.getText().toString());
            paragraphList.get(position).setParagraphText(holder.binding.paragraphText.getText().toString());
            paragraphList.add(new Paragraph(
                    "",
                    "",
                    new ArrayList<>()
            ));
            notifyDataSetChanged();
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
