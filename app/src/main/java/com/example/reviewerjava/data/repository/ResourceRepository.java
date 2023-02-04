package com.example.reviewerjava.data.repository;

import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.model.Item;
import com.example.reviewerjava.data.retrofit.request.FileToSend;
import com.example.reviewerjava.data.retrofit.response.VkResponse;
import com.example.reviewerjava.di.ServiceLocator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResourceRepository {
    public LiveData cacheFile(File file){
        MutableLiveData data = new MutableLiveData();
        new Thread(()->{
            //TODO: saving files to storage;
            data.setValue(null);
        }).start();
        return new MutableLiveData();
    }

    public Item moveToMedia(File parent, Item item){
        new Thread(()->{
            Uri imageUri = Uri.parse(item.getItemImage());
            String imageFileName = imageUri.getLastPathSegment();
            File imageFile = new File(imageUri.getPath());

            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(imageFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if(!parent.exists()) parent.mkdir();
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(new File(parent, imageFileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            save(inputStream, outputStream);

            item.setItemImage(Uri.fromFile(new File(parent, imageFileName)).toString());
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            parent.delete();
        }).start();
        return item;
    }

    public void addToLocalIfRequired(VkResponse.User user) {
        if(!ServiceLocator.getInstance().getUserRepository().userExists(user.name)){
            File file = new File(ServiceLocator.getInstance().getMediaDir().getPath(), user.name);
            ServiceLocator.getInstance().getNetworkRepo().downloadFromInternet(user.imageUri, file);

            user.imageUri = Uri.fromFile(file).toString();
            ServiceLocator.getInstance().getUserRepository().addNewUser(VkResponse.convertToUserEntity(user));
        }
        CurrentUser.getInstance().setUserAndPermission(user.name);
    }

    public void save(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] buffer = new byte[1024 * 50];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) >= 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileToSend getFileToSendInst(Uri uri){
        return new FileToSend(
                getFileName(uri),
                getFileContent(uri)
        );
    }

    private byte[] getFileContent(Uri uri){
        try{
            InputStream inputStream = ServiceLocator.getInstance().getContext().getContentResolver().openInputStream(uri);
            byte[] bytes = new byte[inputStream.available()];

            int bytesRead = 0;
            while(bytesRead >= 0){
                bytesRead = inputStream.read(bytes);
            }
            return bytes;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFileName(Uri uri){
        Cursor cur = ServiceLocator.getInstance().getContext().getContentResolver().query(uri, null, null, null, null);
        if(cur.moveToFirst()){
            int fileNameColumn = cur.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            return cur.getString(fileNameColumn);
        } else{
            return null;
        }
    }
}
