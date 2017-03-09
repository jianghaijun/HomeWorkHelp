package com.example.homeworkhelp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 登录页
 *
 * @author JiangHaiJun
 * @time 2017/3/9 16:30
 */
public class ToastUtil {
	private ToastUtil() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	
	public static Toast toast;
	public static boolean isShow = true;
	
	/**
	 * 短时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (isShow) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	/**
	 * 短时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, int message) {
		if (isShow) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	/**
	 * 长时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (isShow) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	/**
	 * 长时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
		if (isShow) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	/**
	 * 自定义显示Toast时间
	 *
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		if (isShow) {
			toast = Toast.makeText(context, message, duration);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	/**
	 * 自定义显示Toast时间
	 *
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
		if (isShow) {
			toast = Toast.makeText(context, message, duration);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
}
