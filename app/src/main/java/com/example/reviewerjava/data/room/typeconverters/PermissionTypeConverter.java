package com.example.reviewerjava.data.room.typeconverters;

import androidx.room.TypeConverter;

public class PermissionTypeConverter {
    @TypeConverter
    public static Boolean fromInt(int value){
        if(value == 0) return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
