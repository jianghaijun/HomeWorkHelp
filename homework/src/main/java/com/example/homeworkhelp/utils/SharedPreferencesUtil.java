package com.example.homeworkhelp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author JiangHaiJun
 * @time 2017/3/7 10:08
 */

public class SharedPreferencesUtil {
	private static final String SP_NAME = "HomeWork";
	private static SharedPreferences sharedPreferences;
	// 是否是第一启动程序
	public static final String FIRST_OPEN = "isFirstOpen";
	// 是否已经登录
	public static final String IS_LOGIN = "isLogin";

	/**
	 * 数据存储
	 * @param mContext
	 * @param key
	 * @param value
	 */
	public static void storedMessage(Context mContext, String key, Object value){
		sharedPreferences = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		// 判断value类型并存储
		if (value instanceof Integer) {
			editor.putInt(key, Integer.parseInt(String.valueOf(value)));
		} else if (value instanceof String) {
			editor.putString(key, String.valueOf(value));
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, Boolean.valueOf(String.valueOf(value)));
		}
		// 提交
		editor.commit();
	}

	/**
	 * 获取Boolean类型数据
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static boolean getBooleanMessage(Context mContext, String key, boolean strDefault) {
		sharedPreferences = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, strDefault);
	}
	
	/**
	 * 获取String类型数据
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static String getStringMessage(Context mContext, String key) {
		sharedPreferences = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, "");
	}
	
	/**
	 * 获取Int类型数据
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static int getIntMessage(Context mContext, String key, int strDefault){
		sharedPreferences = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getInt(key, strDefault);
	}
}
