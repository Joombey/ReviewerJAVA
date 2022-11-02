package com.example.reviewerjava.data.room.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.User;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.model.Paragraph;
import com.example.reviewerjava.data.model.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity(
        tableName = "reviews",
        foreignKeys = @ForeignKey(
                entity = UserEntity.class,
                parentColumns = "id",
                childColumns = "authorId"
        )
)
public class ReviewEntity extends Review {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int authorId;
    public String item;
    public String paragraphs;

    public int getAuthor() {
        return authorId;
    }


    public void setAuthor(int authorId) {
        this.authorId = authorId;
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

    public List<Paragraph> getRoomParagraphList(){
        Type typeMyType = new TypeToken<ArrayList<Paragraph>>(){}.getType();
        ArrayList<Paragraph> paragraphArrayList = new Gson().fromJson(paragraphs, typeMyType);
        return paragraphArrayList;
    }

    @Override
    public void setParagraphList(List<Paragraph> paragraphList) {
        super.setParagraphList(paragraphList);
        this.paragraphs = new Gson().toJson(paragraphList);
    }

    public static ReviewEntity getInstance(Review review){
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setAuthor(review.getAuthor());
        reviewEntity.setItem(review.getItem());
        reviewEntity.setItem(review.getItem());
        reviewEntity.setParagraphList(review.getParagraphList());
        reviewEntity.setReviewTitle(review.getReviewTitle());
        reviewEntity.setCreationTime(review.getCreationTime());

        return reviewEntity;
    }

    public List<String> getParagraphTitleList(){
        List<String> list = new ArrayList<>();
        List<Paragraph> paragraphList = getRoomParagraphList();
        for(int i = 0; i < paragraphList.size(); i++)  {
            list.add(paragraphList.get(i).getParagraphTitle());
        }
        return list;
    }
}
