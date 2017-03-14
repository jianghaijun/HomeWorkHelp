package com.example.homeworkhelp.view;

import com.example.homeworkhelp.base.BaseView;

/**
 * @author JiangHaiJun
 * @time 2017/3/14 11:20
 */

public interface ForgetPasswordView extends BaseView{
	public void sendSmsCode(String smsCode);
	public void modifyResult(String msg, int code);
}
