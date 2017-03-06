package com.example.homeworkhelp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.homeworkhelp.R;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/3/6
 */
public class BootPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_page);

        Logger.w("测试log打印日志");
    }
}
