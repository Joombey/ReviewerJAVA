package com.example.reviewerjava.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.reviewerjava.data.room.daos.ReviewDAO;
import com.example.reviewerjava.data.room.models.PermissionEntity;
import com.example.reviewerjava.data.room.models.ReportEntity;
import com.example.reviewerjava.data.room.models.ReviewEntity;
import com.example.reviewerjava.data.room.models.UserEntity;
import com.example.reviewerjava.data.room.typeconverters.PermissionTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {
                ReviewEntity.class,
                UserEntity.class,
                PermissionEntity.class,
                ReportEntity.class
        },
        version = 23,
        exportSchema = false
)
@TypeConverters(value = {PermissionTypeConverter.class})
public abstract class ReviewerRoomDb extends RoomDatabase{
    public abstract ReviewDAO reviewDAO();
    private static volatile ReviewerRoomDb INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ReviewerRoomDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReviewerRoomDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ReviewerRoomDb.class, "review_list_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
