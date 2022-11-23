package com.example.reviewerjava.data.retrofit;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.reviewerjava.BuildConfig;
import com.example.reviewerjava.data.CurrentUser;
import com.example.reviewerjava.data.repository.RepositoryController;

import java.io.File;


public class OAuth2 {
    public static final String AUTH_URL =
            "https://oauth.vk.com/authorize" +
            "?client_id=" + BuildConfig.CLIENT_ID +
            "&display=mobile"+
            "&scope=photos,offline" +
            "&redirect_uri=https://oauth.vk.com/blank.html&display=mobile&response_type=token";

    public static final String RESPONSE_URL_PATTERN = "https://oauth.vk.com/blank.html";

    public static WebViewClient getWebViewClient(File parentPath){
        return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String urlString = request.getUrl().toString();
                if(urlString.contains(RESPONSE_URL_PATTERN)){
                    CurrentUser.getInstance().access_token = Uri.parse(urlString.replace("#", "?")).getQueryParameter("access_token");
                    CurrentUser.getInstance().userId = Uri.parse(urlString.replace("#", "?")).getQueryParameter("user_id");
                    RepositoryController.getUserInfo(parentPath);
                    return false;
                }
                view.loadUrl(urlString);
                return true;
            }
        };
    }
}
