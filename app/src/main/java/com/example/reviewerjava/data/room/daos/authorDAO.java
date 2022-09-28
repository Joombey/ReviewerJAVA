package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.reviewerjava.data.room.model.Author;

@Dao
public interface authorDAO {
    @Query("SELECT * FROM authors")
    MutableLiveData<Author> getAllAuthors();

    @Insert
    void insertAuthor(Author author);
}
