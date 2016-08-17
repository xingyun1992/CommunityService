package com.sz.communityservice.utils;

import android.util.Log;

/**
 * Created by xingyun on 2016/8/9.
 */
public class Utils {

    public static boolean isdebug = true;
    public static String tag = "xingyun";


    public static void printLog(String msg) {
        if (isdebug) {
            Log.e(tag, msg);
        }
    }
}
