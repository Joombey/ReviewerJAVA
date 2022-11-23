package com.example.reviewerjava.ui.viewmodel;

import android.webkit.WebViewClient;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;

import java.io.File;

public class OAuthVkViewModel extends ViewModel {

    public WebViewClient getClient(File parentPath) {
        return RepositoryController.getClient(parentPath);
    }
}
