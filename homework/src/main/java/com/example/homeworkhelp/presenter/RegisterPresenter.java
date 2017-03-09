package com.example.homeworkhelp.presenter;

import android.content.Context;

import com.example.homeworkhelp.base.BasePresenter;
import com.example.homeworkhelp.utils.PhoneFormatCheckUtils;
import com.example.homeworkhelp.utils.ToastUtil;
import com.example.homeworkhelp.view.RegisterView;

/**
 * @author JiangHaiJun
 * @time 2017/3/9 16:27
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
	/**
	 * 注册
	 * @param mContext
	 * @param userPhone
	 * @param password
	 * @param confirmPassword
	 */
	public void register(Context mContext, String userPhone, String password, String confirmPassword){
		if (userPhone.isEmpty()) {
			ToastUtil.showShort(mContext, "手机号不能为空!");
		} else if (!PhoneFormatCheckUtils.isPhoneLegal(userPhone)) {
			ToastUtil.showShort(mContext, "请输入正确的手机号!");
		} else if (password.isEmpty()) {
			ToastUtil.showShort(mContext, "密码不能为空!");
		} else if (!password.equals(confirmPassword)) {
			ToastUtil.showShort(mContext, "两次输入的密码不一致!");
		} else {
			// 注册
			mView.registerState(true, "注册成功!");
		}
	}
}
