package com.sz.communityservice.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;


public class SysInfo {

	/**
	 * 获取sdk版本号 2.1: 7; 2.2: 8; 2.3.1: 9; 2.3.4: 10
	 * 
	 * @return
	 */
	static public int getSDK() {
		return Integer.valueOf(android.os.Build.VERSION.SDK);
	}

	/**
	 * 获取手机型号 如: Nexus One,I9100
	 * 
	 * @return
	 */
	static public String getModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获取发布版本号 2.1;2.2;2.3.4...
	 * 
	 * @return
	 */
	static public String getRelease() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取CPU处理器型号，如：armeabi-v7a
	 */
	static public String getCpu() {
        return android.os.Build.CPU_ABI;
    }
	/**
	 * 获取手机制造商，如：samsung
	 */
	static public String getManuFacturer() {
	    return android.os.Build.MANUFACTURER;
	}
	/**
	 * 获取商标，如：samsung
	 */
	static public String getBrand() {
	    return android.os.Build.BRAND;
	}
	/**
	 * 获取主板,如：GT-I9100
	 */
	static public String getBoard() {
	    return android.os.Build.BOARD;
	}
	/**
	 * 获取用户,如：root
	 */
	static public String getUser() {
	    return android.os.Build.USER;
	}
	/**
	 * 获取屏幕宽高
	 * @return
	 */
	static public int getWidth(Context ctx)
	{
		DisplayMetrics dm = new DisplayMetrics();

		((Activity)ctx).getWindowManager().getDefaultDisplay().getMetrics(dm);

		int width = dm.widthPixels;

		int height = dm.heightPixels;
		return width;
	}
	
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    } 
}
