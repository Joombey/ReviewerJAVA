package com.example.reviewerjava.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
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
    private boolean writeAccess;

    public ParagraphListAdapter(List<Paragraph> paragraphList, MainActivity activity, Scroller scroller, boolean writeAccess){
        this.paragraphList = paragraphList;
        this.activity = activity;
        this.scroller = scroller;
        this.writeAccess = writeAccess;
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
        if(paragraphList.get(position).getImages().size() == 0 && !writeAccess){
            holder.binding.imageSlider.setVisibility(View.GONE);
        }
        holder.binding.imageSlider
                .setAdapter(
                        new ImageSliderAdapter(
                                paragraphList.get(position).getImages(), writeAccess, activity
                        )
                );

        if(writeAccess) {
            holder.binding.paragraphTitle.setText(paragraphList.get(position).getParagraphTitle());
            holder.binding.paragraphText.setText(paragraphList.get(position).getParagraphText());



            holder.binding.paragraphText.setOnFocusChangeListener((view, b) -> {
                if (!b) {
                    save(position, holder.binding.paragraphText.getText().toString(), false);
                    holder.binding.saveButton.setVisibility(View.GONE);
                } else holder.binding.saveButton.setVisibility(View.VISIBLE);
            });

            holder.binding.paragraphTitle.setOnFocusChangeListener((v, b) -> {
                if (!b) {
                    save(position, holder.binding.paragraphTitle.getText().toString(), true);
                    holder.binding.saveButton.setVisibility(View.GONE);
                } else holder.binding.saveButton.setVisibility(View.VISIBLE);
            });

            holder.binding.nextParagraphBtn.setOnClickListener(v -> {
                save(
                        position,
                        holder.binding.paragraphTitle.getText().toString(),
                        holder.binding.paragraphText.getText().toString()
                );
                holder.binding.saveButton.setVisibility(View.GONE);
                scroller.addItem(position + 1);
            });

            holder.binding.saveButton.setOnClickListener(v -> {
                save(
                        position,
                        holder.binding.paragraphTitle.getText().toString(),
                        holder.binding.paragraphText.getText().toString()
                );
            });
        } else{
            if (paragraphList.get(position).getImages().size() == 0) holder.binding.imageSlider.setVisibility(View.GONE);
            holder.binding.title.setText(paragraphList.get(position).getParagraphTitle());
            holder.binding.text.setText(paragraphList.get(position).getParagraphText());
        }
    }

    public void save(int position, String data, boolean isTitle){
        if (isTitle){
            paragraphList.get(position).setParagraphTitle(data);
            return;
        }
        else paragraphList.get(position).setParagraphText(data);
    }

    public void save(int position, String title, String text){
        paragraphList.get(position).setParagraphTitle(title);
        paragraphList.get(position).setParagraphText(text);
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
            if(!writeAccess){
                binding.nextParagraphBtn.setVisibility(View.GONE);
                binding.saveButton.setVisibility(View.GONE);
                binding.paragraphText.setVisibility(View.GONE);
                binding.paragraphTitle.setVisibility(View.GONE);

                binding.title.setVisibility(View.VISIBLE);
                binding.text.setVisibility(View.VISIBLE);
            }
        }
    }
}
