package com.example.reviewerjava.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.reviewerjava.data.room.daos.ReviewDAO;
import com.example.reviewerjava.data.room.models.ReviewEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ReviewEntity.class}, version = 5, exportSchema = false)

public abstract class ReviewListRoomDataBase extends RoomDatabase{
    public abstract ReviewDAO reviewDAO();
    private static volatile ReviewListRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ReviewListRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReviewListRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ReviewListRoomDataBase.class, "review_list_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
