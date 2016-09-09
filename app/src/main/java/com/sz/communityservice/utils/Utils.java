package com.sz.communityservice.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    public static void showToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
