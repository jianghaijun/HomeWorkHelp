package com.example.homeworkhelp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.custom.Guideview;
import com.example.homeworkhelp.utils.SharedPreferencesUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 引导页
 * @author JiangHaiJun
 * @time 2017/3/6 21:08
 */
public class BootPageActivity extends AppCompatActivity {
	@ViewInject(R.id.guideView)
	private Guideview guideView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot_page);
		x.view().inject(this);
		initView();
	}

	/**
	 * 初始化
	 */
	private void initView() {
		guideView.setData(R.drawable.guide1_iv1, R.drawable.guide1_iv2, R.drawable.guide1_iv3);
		guideView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BootPageActivity.this, WelcomeActivity.class);
				startActivity(intent);
				// 更改SharedPreferences
				SharedPreferencesUtil.storedMessage(BootPageActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
				finish();
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// 如果切换到后台，就设置下次不进入功能引导页
		SharedPreferencesUtil.storedMessage(BootPageActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
		finish();
	}
}
