package com.example.reviewerjava.data.repository.sources;

import androidx.lifecycle.LiveData;

import com.example.reviewerjava.data.model.Item;

import java.io.File;
import java.util.List;

public interface ItemSourceOfTruth {
    LiveData<List<Item>> getItemsByRequest(String q, File cacheDir);
}
