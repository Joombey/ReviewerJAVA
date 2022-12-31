package com.example.reviewerjava.presentation.view.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.databinding.ItemListElementBinding;
import com.example.reviewerjava.utils.ItemSetter;

import java.io.File;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemArrayViewHolder> {
    private final List<Item> items;
    private final ItemSetter itemSetter;
    private final Context context;

    public ItemListAdapter(ItemSetter itemSetter, List<Item> items, Context context) {
        this.items = items;
        this.context = context;
        this.itemSetter = itemSetter;
    }

    @NonNull
    @Override
    public ItemArrayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListElementBinding itemListElementBinding = ItemListElementBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ItemArrayViewHolder(itemListElementBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemArrayViewHolder holder, int position) {
        new Thread(()->{
            File file =  new File(
                    context.getCacheDir().getPath(),
                    items.get(holder.getBindingAdapterPosition()).getProductId() + ".png"
            );
            if(file.exists()) {
                holder.binding.itemImage.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                items.get(holder.getBindingAdapterPosition()).setItemImage(Uri.fromFile(file).toString());
            }
        }).run();
        holder.binding.getRoot().setOnClickListener(view -> {
            itemSetter.setItem(items.get(position));
        });
        holder.binding.itemDescription.setText(items.get(position).getDescription());
        holder.binding.itemTitle.setText(items.get(position).getItemName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemArrayViewHolder extends RecyclerView.ViewHolder{
        private final ItemListElementBinding binding;
        public ItemArrayViewHolder(ItemListElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
