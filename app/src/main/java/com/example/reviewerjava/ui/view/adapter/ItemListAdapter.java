package com.example.reviewerjava.ui.view.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.databinding.ItemListElementBinding;
import com.example.reviewerjava.utils.ItemSetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemArrayViewHolder> {
    private List<Item> items;
    private ItemSetter itemSetter;
    private Context context;

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
            File file =  new File(context.getCacheDir().getPath(), items.get(holder.getAdapterPosition()).getProductId() + ".png");
            if(file.exists()) {
                holder.binding.itemImage.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                items.get(holder.getAdapterPosition()).setItemImage(Uri.fromFile(file).toString());
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
        private ItemListElementBinding binding;
        public ItemArrayViewHolder(ItemListElementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
