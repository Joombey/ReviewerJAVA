package com.example.reviewerjava.data.room.daos;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.reviewerjava.data.room.models.ItemEntity;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM ItemEntity WHERE productId=:productId")
    List<ItemEntity> getItemList(String productId);

}
