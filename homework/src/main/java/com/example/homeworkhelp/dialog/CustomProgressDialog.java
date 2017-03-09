package com.example.homeworkhelp.dialog;

/**
 * @author JiangHaiJun
 * @time 2017/3/9 11:28
 */

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.homeworkhelp.R;

/**
 * 自定义加载旋转动画
 *
 * @author JiangHaiJun
 * @date 2016-5-31
 */
public class CustomProgressDialog extends Dialog {
	/* 是否已经展示 */
	private boolean isShow = true;
	
	/**
	 * 自定义Dialog样式
	 *
	 * @param context
	 */
	public CustomProgressDialog(Context context) {
		super(context, R.style.Custom_Progress);
	}
	
	/**
	 * 展示Dialog
	 *
	 * @param isCancelable 点击dialog外部是否关闭
	 */
	public void show(boolean isCancelable) {
		if (isShow) {
			setContentView(R.layout.custom_progress_dialog);
			// 按返回键是否取消
			setCancelable(isCancelable);
			// 设置居中
			getWindow().getAttributes().gravity = Gravity.CENTER;
			WindowManager.LayoutParams lp = getWindow().getAttributes();
			// 设置背景层透明度
			lp.dimAmount = 0.2f;
			getWindow().setAttributes(lp);
			isShow = false;
		}
		super.show();
	}

}