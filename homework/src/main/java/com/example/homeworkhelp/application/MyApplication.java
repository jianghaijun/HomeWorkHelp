package com.example.homeworkhelp.application;

import com.orhanobut.logger.Logger;

import org.litepal.LitePalApplication;
import org.xutils.x;

/**
 * @author JiangHaiJun
 * @time 2017/3/6 21:10
 */

public class MyApplication extends LitePalApplication {
    private static String TAG = "HomeWork";
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 自定义Log--TAG
         * 设置不打印Log---logLevel(LogLevel.NONE);
         */
        Logger.init(TAG);
        // 初始化XUtils
        x.Ext.init(this);
    }
}
