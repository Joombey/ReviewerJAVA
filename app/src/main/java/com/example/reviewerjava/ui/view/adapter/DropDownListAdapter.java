package com.example.reviewerjava.ui.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.reviewerjava.data.room.models.ReviewRoom;

import java.util.List;

public class DropDownListAdapter extends ArrayAdapter<List<ReviewRoom>> {

    private List<ReviewRoom> reviewRooms;

    public DropDownListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return null;
    }
}
