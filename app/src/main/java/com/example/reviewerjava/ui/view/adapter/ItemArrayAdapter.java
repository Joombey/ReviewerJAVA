package com.example.reviewerjava.ui.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.databinding.ItemListElementBinding;

import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<Item>{
    private List<Item> items;
    private ItemListElementBinding mBinding;
    private Context context;
    public ItemArrayAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mBinding = ItemListElementBinding.inflate(LayoutInflater.from(context), parent, false);
        mBinding.itemImage.setImageBitmap(
                BitmapFactory.decodeFile(
                        String.valueOf(
                                Uri
                                        .parse(items.get(position).getItemImage())
                                        .getPath()
                        )
                )
        );
        mBinding.itemTitle.setText(
                items.get(position).getItemName()
        );
        mBinding.itemDescription.setText(
                items.get(position).getDesctiption()
        );
        return mBinding.getRoot();
    }
}
