package com.example.homeworkhelp.presenter;

import android.content.Context;

import com.example.homeworkhelp.base.BasePresenter;
import com.example.homeworkhelp.utils.ToastUtil;
import com.example.homeworkhelp.view.LoginView;

/**
 * @author Administrator
 * @time 2017/3/7 0007 22:00
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    /**
     * 登录
     * @param mContext
     * @param userName
     * @param password
     */
    public void login (Context mContext, String userName, String password) {
        if (userName.isEmpty()) {
            ToastUtil.showShort(mContext, "手机号不能为空!");
        } else if (password.isEmpty()) {
            ToastUtil.showShort(mContext, "密码不能为空!");
        } else {
            mView.login(true);
        }
    }
}
