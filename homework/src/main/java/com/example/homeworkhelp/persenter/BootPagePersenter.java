package com.example.homeworkhelp.persenter;

import com.example.homeworkhelp.base.BasePresenter;
import com.example.homeworkhelp.view.BootPageView;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class BootPagePersenter extends BasePresenter<BootPageView> {
    public void show(){
        mView.showToast();
    }
}
