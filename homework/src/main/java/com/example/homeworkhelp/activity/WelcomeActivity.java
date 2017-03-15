package com.example.homeworkhelp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.custom.CircleTextProgressbar;
import com.example.homeworkhelp.utils.SharedPreferencesUtil;

import org.litepal.tablemanager.Connector;

/**
 * 欢迎页
 * @author JiangHaiJun
 * @time 2017/3/7 10:08
 */
public class WelcomeActivity extends AppCompatActivity {
	private MyCountDownTimer downTimer;
	private CircleTextProgressbar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 判断是否是第一次启动程序
		boolean isFirstOpen = SharedPreferencesUtil.getBooleanMessage(this, SharedPreferencesUtil.FIRST_OPEN, true);
		if (isFirstOpen) {
			// 创建数据库
			Connector.getDatabase();
			// 进入引导页
			startActivity(new Intent(this, BootPageActivity.class));
			this.finish();
			return;
		}
		
		// 不是第一次启动
		setContentView(R.layout.activity_welcome);
		// 初始化控件
		initView();
		// 倒计时3s
		downTimer = new MyCountDownTimer(3000, 1000);
		downTimer.start();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// 圆环进度条
		progressBar = (CircleTextProgressbar) findViewById(R.id.progressBar);
		progressBar.setOutLineColor(Color.TRANSPARENT);
		progressBar.setInCircleColor(Color.parseColor("#AAC6C6C6"));
		progressBar.setProgressColor(Color.DKGRAY);
		progressBar.setProgressLineWidth(10);
		progressBar.start();
		
		progressBar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (downTimer != null) {
					downTimer.cancel();
				}
				// 停止progressBar
				progressBar.stop();
				// 进入下一个界面
				nextActivity();
			}
		});
	}
	
	/**
	 * 倒计时
	 */
	class MyCountDownTimer extends CountDownTimer{
		/**
		 * @param millisInFuture		表示以毫秒为单位 倒计时的总数
		 * @param countDownInterval		表示间隔多少毫秒调用一次onTick方法
		 */
		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}
		
		@Override
		public void onTick(long millisUntilFinished) {
			
		}
		
		@Override
		public void onFinish() {
			nextActivity();
		}
	}

	/**
	 * 进入下一个界面
	 */
	private void nextActivity(){
		// 判断是否已经登录
		boolean isLogin = SharedPreferencesUtil.getBooleanMessage(WelcomeActivity.this, SharedPreferencesUtil.IS_LOGIN, false);
		if (isLogin) {
			// 进入主界面
			startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
			WelcomeActivity.this.finish();
		} else {
			// 进入登录界面
			startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
			WelcomeActivity.this.finish();
		}
	}

	/**
	 * 屏蔽物理返回按钮
	 * @param keyCode
	 * @param event
	 * @return
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (downTimer != null) {
			downTimer = null;
		}
	}
}
