package com.example.homeworkhelp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.homeworkhelp.R;
import com.example.homeworkhelp.base.BaseActivity;
import com.example.homeworkhelp.dialog.CustomProgressDialog;
import com.example.homeworkhelp.presenter.LoginPresenter;
import com.example.homeworkhelp.utils.SharedPreferencesUtil;
import com.example.homeworkhelp.view.LoginView;
import com.liji.circleimageview.CircleImageView;
import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 登录页
 * @author JiangHaiJun
 * @time 2017/3/7 10:08
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
	// 圆形图片
	@ViewInject(R.id.userHeadIv)
	private CircleImageView userHeadIv;
	// 手机号
	@ViewInject(R.id.userNameEdt)
	private EditText userNameEdt;
	// 密码
	@ViewInject(R.id.passwordEdt)
	private EditText passwordEdt;

	private Context mContext;
	private CustomProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		x.view().inject(this);
		
		mContext = this;
		
		progressDialog = new CustomProgressDialog(LoginActivity.this);
		Glide.with(this).load("http://s15.sinaimg.cn/mw690/bf131490gd817c13a0dfe&690").into(userHeadIv);
	}

	@Override
	public LoginPresenter initPresenter() {
		return new LoginPresenter();
	}

	@Override
	public void showLoading() {
		progressDialog.show(true);
	}

	@Override
	public void hideLoading() {
		progressDialog.dismiss();
	}

	@Override
	public void login(boolean isSuccessful, String msg) {
		if (isSuccessful) {
			Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
            // 登录成功后修改为已登录状态
            SharedPreferencesUtil.storedMessage(LoginActivity.this, SharedPreferencesUtil.IS_LOGIN, true);
			startActivity(new Intent(mContext, MainActivity.class));
			this.finish();
		} else {
			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 点击事件
	 * @param view
	 */
	@Event({R.id.userHeadIv, R.id.loginBtn, R.id.registeredBtn, R.id.forgetPasswordBtn})
	private void onClick(View view){
		switch (view.getId()) {
			// 选择用户
			case R.id.userHeadIv:
				startActivityForResult(new Intent(LoginActivity.this, ChoiceUserActivity.class), 3);
				break;
			// 登录
			case R.id.loginBtn:
				presenter.login(mContext, userNameEdt.getText().toString().trim(), passwordEdt.getText().toString().trim());
				break;
			// 注册
			case R.id.registeredBtn:
				startActivityForResult(new Intent(LoginActivity.this, RegisteredActivity.class), 1);
				break;
			// 忘记密码
			case R.id.forgetPasswordBtn:
				startActivityForResult(new Intent(LoginActivity.this, ForgetPasswordActivity.class), 2);
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (resultCode) {
			case 1:
				switch (requestCode) {
					case 1:
						Logger.w("新用户注册成功!");
						Logger.w(data.getExtras().getString("userPhone"));
						break;
					case 2:
						Logger.w("修改密码成功!");
						Logger.w(data.getExtras().getString("userPhone"));
						break;
					case 3:
						Logger.w("用户选择!");
						break;
				}
				break;
			default:
				break;
		}
	}
}
