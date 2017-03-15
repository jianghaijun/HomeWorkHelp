package com.example.homeworkhelp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.utils.SharedPreferencesUtil;

/**
 * 主界面
 * @author JiangHaiJun
 * @time 2017/3/7 14:55
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 登录成功后修改为已登录状态
		SharedPreferencesUtil.storedMessage(MainActivity.this, SharedPreferencesUtil.IS_LOGIN, false);
	}
}
