package com.example.reviewerjava.data.room.roomModels;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Author;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Review;
import com.google.gson.Gson;

@Entity(tableName = "reviews")
public class ReviewRoom extends Review {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String author;
    public String item;

    @Override
    public Author getAuthor() {
        return new Gson().fromJson(this.author, Author.class);
    }

    @Override
    public void setAuthor(Author author) {
        super.setAuthor(author);
        this.author = new Gson().toJson(author, Author.class);
    }

    @Override
    public Item getItem() {
        return new Gson().fromJson(this.item, Item.class);
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        this.item = new Gson().toJson(item, Item.class);
    }

    public static ReviewRoom getInstance(Review review){
        ReviewRoom reviewRoom = new ReviewRoom();
        reviewRoom.setAuthor(review.getAuthor());
        reviewRoom.setItem(review.getItem());
        reviewRoom.setItem(review.getItem());
        reviewRoom.setText(review.getText());
        reviewRoom.setTitle(review.getTitle());
        reviewRoom.setPicture(review.getPicture());
        reviewRoom.setCreationTime(review.getCreationTime());
        return reviewRoom;
    }
}
