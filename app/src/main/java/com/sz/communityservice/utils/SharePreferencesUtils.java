package com.sz.communityservice.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xingyun on 2016/9/13.
 */
public class SharePreferencesUtils {

    private static final String filename = "config";

    private static final String USERID = "userid";
    private  static final String USERNAME = "username";

    public static void setUserId(Context context,String userid){
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USERID,userid).apply();
    }
    public static String getUserId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERID,"");
    }
    public static void setUsername(Context context,String username){
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USERNAME,username).apply();
    }
    public static String getUsername(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME,"");
    }

}
