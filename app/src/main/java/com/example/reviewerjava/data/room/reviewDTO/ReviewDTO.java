package com.example.reviewerjava.data.room.reviewDTO;


import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Review;



public class ReviewDTO extends Review {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String text;
    public String creationTime;
    public String author;
    public String picture;
    public String item;
}
