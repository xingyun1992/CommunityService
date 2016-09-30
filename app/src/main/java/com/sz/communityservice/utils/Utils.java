package com.sz.communityservice.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

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

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String  getUid(Context context) {
        String PREFS_FILE = "device_id";
        String PREFS_DEVICE_ID = "device_id";
        UUID uuid = null;
        final SharedPreferences prefs = context
                .getSharedPreferences(PREFS_FILE, 0);
        final String id = prefs.getString(PREFS_DEVICE_ID, null);

        if (id != null) {
            uuid = UUID.fromString(id);
        } else {

            final String androidId = Settings.Secure
                    .getString(context.getContentResolver(),
                            Settings.Secure.ANDROID_ID);
            try {
                if (!"9774d56d682e549c".equals(androidId)) {
                    uuid = UUID.nameUUIDFromBytes(androidId
                            .getBytes("utf8"));
                } else {
                    //设备的imei号
                    final String deviceId = ((TelephonyManager) context
                            .getSystemService(Context.TELEPHONY_SERVICE))
                            .getDeviceId();
                    uuid = deviceId != null ? UUID
                            .nameUUIDFromBytes(deviceId
                                    .getBytes("utf8")) : UUID
                            .randomUUID();
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            // Write the value out to the prefs file
            prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();

        }
        if (uuid!=null){
            return uuid.toString();
        }else
            return "";

    }

    public static String msgAddHead(String msg){
        int msglength = msg.length();
        String msghex = Integer.toHexString(msglength);
        int msghexlenght = msghex.length();
        if (msghexlenght==1){
            msghex = "000"+msghex;
        }else if (msghexlenght==2){
            msghex = "00"+msghex;
        }else if (msghexlenght==3){
            msghex = "0"+msghex;
        }

        printLog("发送的字符串长度为："+msghex);

        msg = msghex + msg;
        return msg;
    }

    public static byte[] msgAddHead(int length){

        byte[] head = new byte[4];

        head[0] = (byte) (length>>>24);
        head[1]  = (byte) (length>>>16);
        head[2]  = (byte) (length>>>8);
        head[3]  = (byte) (length);

        return head;

    }

}
