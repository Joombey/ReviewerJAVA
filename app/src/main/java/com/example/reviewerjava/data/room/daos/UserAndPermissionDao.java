package com.example.reviewerjava.data.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.relation.UserAndPermission;

import java.util.List;

@Dao
public interface UserAndPermissionDao {
    @Query("SELECT * FROM users WHERE name = :userName")
    UserEntity getUserByName(String userName);

    @Query("SELECT * FROM permissions WHERE role = :role")
    PermissionEntity getPermission(String role);

    @Query("SELECT * FROM users WHERE name !=:name")
    LiveData<List<UserEntity>> getUsers(String name);

    @Transaction
    @Query("SELECT * FROM users WHERE name == :name")
    UserAndPermission getUser(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserList(List<UserEntity> newUserList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPermission(PermissionEntity permission);

    @Transaction
    @Insert
    default void insertUserAndPermission(UserAndPermission userAndPermission){
        insertPermission(userAndPermission.permission);
        insertUser(userAndPermission.user);
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUserState(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);
}
