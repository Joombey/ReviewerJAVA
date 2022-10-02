package com.example.reviewerjava.data.room.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Author;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Paragraph;
import com.example.reviewerjava.data.model.Review;
import com.google.gson.Gson;

import java.util.List;

@Entity(tableName = "reviews")
public class ReviewRoom extends Review {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String author;
    public String item;
    public String paragraphs;

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

    @Override
    public List<Paragraph> getParagraphList() {
        return new Gson().fromJson(this.paragraphs, List.class);
    }

    @Override
    public void setParagraphList(List<Paragraph> paragraphList) {
        super.setParagraphList(paragraphList);
        this.paragraphs = new Gson().toJson(paragraphList);
    }

    public static ReviewRoom getInstance(Review review){
        ReviewRoom reviewRoom = new ReviewRoom();

        reviewRoom.setAuthor(review.getAuthor());
        reviewRoom.setItem(review.getItem());
        reviewRoom.setItem(review.getItem());
        reviewRoom.setParagraphList(review.getParagraphList());
        reviewRoom.setReviewTitle(review.getReviewTitle());
        reviewRoom.setCreationTime(review.getCreationTime());

        return reviewRoom;
    }


}
