package com.example.homeworkhelp.presenter;

import com.example.homeworkhelp.base.BasePresenter;
import com.example.homeworkhelp.view.BootPageView;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class BootPagePresenter extends BasePresenter<BootPageView> {
    public void show(){
        mView.showToast();
    }
}
