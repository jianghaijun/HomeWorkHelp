package com.example.homeworkhelp.view;

import com.example.homeworkhelp.base.BaseView;

/**
 * 登录
 * @author Administrator
 * @time 2017/3/7 19:46
 */

public interface LoginView extends BaseView{
    /**
     * 是否登录成功
     * @param isSuccessful
     * @param msg
     */
    public void login(boolean isSuccessful, String msg);
}
