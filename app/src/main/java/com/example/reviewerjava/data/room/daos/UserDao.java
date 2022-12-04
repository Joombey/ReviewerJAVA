package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.reviewerjava.data.repository.sources.UserSourceOfTruth;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

@Dao
public interface UserDao extends UserSourceOfTruth {

    @Override
    @Query("SELECT * FROM users WHERE name = :userName")
    UserEntity getUserByName(String userName);

    @Override
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUserState(UserEntity user);

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Override
    @Query("SELECT * FROM users WHERE name !=:name")
    LiveData<List<UserEntity>> getUsers(String name);

    @Override
    @Delete
    void deleteUser(UserEntity user);

    @Override
    @Transaction
    @Query("SELECT * FROM users WHERE name == :name")
    UserAndPermission getUser(String name);

}
