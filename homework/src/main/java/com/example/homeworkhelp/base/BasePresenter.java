package com.example.homeworkhelp.base;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}
