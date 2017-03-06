package com.example.homeworkhelp.application;

import android.nfc.Tag;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017/3/6 0006.
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
    }
}
