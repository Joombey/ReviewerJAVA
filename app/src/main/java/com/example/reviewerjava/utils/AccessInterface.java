package com.example.reviewerjava.utils;

import android.view.View;

public interface AccessInterface {
    default boolean getBanRequestListPermission(){
        return false;
    }

    default boolean getBanListPermission(){
        return false;
    }

    default boolean getModeratorReportList(){
        return false;
    }

    default int getDeleteReviewPermission(){
        return View.GONE;
    }
}
