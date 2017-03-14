package com.example.homeworkhelp.presenter;

import android.content.Context;

import com.example.homeworkhelp.base.BasePresenter;
import com.example.homeworkhelp.utils.PhoneFormatCheckUtils;
import com.example.homeworkhelp.utils.ToastUtil;
import com.example.homeworkhelp.view.ForgetPasswordView;
import com.orhanobut.logger.Logger;

/**
 * @author JiangHaiJun
 * @time 2017/3/14 11:24
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordView> {
	private int smsCode;
	/**
	 * 发送手机验证码
	 */
	public void sendSmsCode(){
		smsCode = (int) ((Math.random()*9+1) * 100000);
		Logger.w("短信验证码为:" + smsCode);
		mView.sendSmsCode("短信验证码为:" + smsCode);
	}

	/**
	 * 修改密码
	 * @param userPhone
	 * @param newPassWord
	 * @param confirmNewPassWord
	 * @param smsCode
	 */
	public void modifyPassWord(Context mContext, String userPhone, String newPassWord, String confirmNewPassWord, String smsCode){
		if (userPhone.isEmpty()) {
			ToastUtil.showShort(mContext, "请输入手机号码!");
		} else if (!PhoneFormatCheckUtils.isPhoneLegal(userPhone)) {
			ToastUtil.showShort(mContext, "请输入正确的手机号码!");
		} else if (newPassWord.isEmpty()) {
			ToastUtil.showShort(mContext, "请输入新密码!");
		} else if (!newPassWord.equals(confirmNewPassWord)) {
			ToastUtil.showShort(mContext, "两次输入密码不一致,请核对新密码!");
		} else if (!String.valueOf(this.smsCode).equals(smsCode)) {
			ToastUtil.showShort(mContext, "验证码不正确!");
		} else {
			mView.modifyResult("成功", 0);
		}
	}
}
