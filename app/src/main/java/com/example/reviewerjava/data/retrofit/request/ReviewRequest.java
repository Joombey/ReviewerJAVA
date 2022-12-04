package com.example.reviewerjava.data.retrofit.request;

import com.example.reviewerjava.data.model.Paragraph;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReviewRequest {
    public int id;
    public String author;
    public String item;
    public List<Paragraph> paragraphs;

    public ReviewRequest(int id, String author, String item, String paragraphs) {
        this.id = id;
        this.author = author;
        this.item = item;
        this.paragraphs = new Gson().fromJson(
                paragraphs,
                new TypeToken<ArrayList<Paragraph>>(){}.getType()
        );
    }
}

