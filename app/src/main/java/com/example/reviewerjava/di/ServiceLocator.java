package com.example.reviewerjava.di;

import android.app.Application;

import androidx.room.Room;

import com.example.reviewerjava.data.repository.RoomRepository;
import com.example.reviewerjava.data.repository.sources.ItemSourceOfTruth;
import com.example.reviewerjava.data.repository.sources.UserSourceOfTruth;
import com.example.reviewerjava.data.retrofit.base.ShoppingApiBase;

import java.io.File;

public class ServiceLocator {
    private static ServiceLocator instance;
    private ItemSourceOfTruth itemSourceOfTruth;
    private File cacheDir;
    private File mediaDir;
    private RoomRepository roomRepo;

    ServiceLocator(){};

    public static ServiceLocator getInstance() {
        if (instance == null){
            instance = new ServiceLocator();
        }
        return instance;
    }

    public ItemSourceOfTruth getItemSourceOfTruth(){
        if (itemSourceOfTruth == null) {
            itemSourceOfTruth = new ShoppingApiBase();
        }
        return itemSourceOfTruth;
    }

    public UserSourceOfTruth getUserSourceOfTruth(){

        return null;
    }

    public void init(Application app){
        cacheDir = app.getCacheDir();
        mediaDir = app.getExternalMediaDirs()[0];
        roomRepo = new RoomRepository(app);
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public File getMediaDir() {
        return mediaDir;
    }

    public RoomRepository getLocalBase() {
        return roomRepo;
    }
}
