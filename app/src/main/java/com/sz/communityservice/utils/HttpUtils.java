package com.sz.communityservice.utils;

import android.os.Environment;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;

/**
 * Created by xingyun on 2016/8/9.
 */
public class HttpUtils {


    public static void postfile() {
        File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        try {
            uploadFile(file, "http://120.25.160.18:4869/upload");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void uploadFile(File file, String url) throws Exception {
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("userfile", file);
//            params.put("uploadType","jpg");
            // 上传文件
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String s) {
                    super.onSuccess(s);
                    Log.e("xingyun",s);
                }
            });
        }
    }
    public static void get(String url){


    }

}
