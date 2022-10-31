package com.example.reviewerjava.data.room.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.reviewerjava.data.model.Item;


@Entity(foreignKeys = {
        @ForeignKey(
                entity = ReviewEntity.class,
                parentColumns = "id",
                childColumns = "productId"
        )
})
public class ItemEntity extends Item {
    @PrimaryKey()
    public String productId;
    public String title;
    public String source;
    public String imageUrl;
    public String description;
}
