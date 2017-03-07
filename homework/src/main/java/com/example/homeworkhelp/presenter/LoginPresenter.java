package com.example.homeworkhelp.presenter;

import com.example.homeworkhelp.base.BasePresenter;
import com.example.homeworkhelp.view.LoginView;

/**
 * @author Administrator
 * @time 2017/3/7 0007 22:00
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    public void login (String userName, String password) {
        mView.login(true);
    }
}
