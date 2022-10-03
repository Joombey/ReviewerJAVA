package com.example.reviewerjava.utils;

import androidx.recyclerview.widget.RecyclerView;

public interface Scroller {
    void scrollTo(int itemPosition);
    void addItem(int position);
}
