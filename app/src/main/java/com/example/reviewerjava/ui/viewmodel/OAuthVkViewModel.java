package com.example.reviewerjava.ui.viewmodel;

import android.webkit.WebViewClient;

import androidx.lifecycle.ViewModel;

import com.example.reviewerjava.data.repository.RepositoryController;
import com.example.reviewerjava.data.retrofit.OAuth2;

import java.io.File;

public class OAuthVkViewModel extends ViewModel {

    public WebViewClient getClient(File parentPath, OAuth2.Back back) {
//        return RepositoryController.getClient(parentPath, back);
        return OAuth2.getWebViewClient(parentPath, back);
    }
}
