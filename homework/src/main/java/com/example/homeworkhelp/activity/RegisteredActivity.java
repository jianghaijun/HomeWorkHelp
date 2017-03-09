package com.example.homeworkhelp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.base.BaseActivity;
import com.example.homeworkhelp.dialog.CustomProgressDialog;
import com.example.homeworkhelp.presenter.RegisterPresenter;
import com.example.homeworkhelp.utils.ToastUtil;
import com.example.homeworkhelp.view.RegisterView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 注册页
 * @author JiangHaiJun
 * @time 2017/3/9 15:12
 */
public class RegisteredActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {
	@ViewInject(R.id.txtTitle)
	private TextView txtTitle;
	@ViewInject(R.id.imgBtnLeft)
	private ImageButton imgBtnLeft;

	@ViewInject(R.id.registerPhoneEdt)
	private EditText registerPhoneEdt;
	@ViewInject(R.id.registerPassWordEdt)
	private EditText registerPassWordEdt;
	@ViewInject(R.id.confirmPassWordEdt)
	private EditText confirmPassWordEdt;

	private Context mContext;
	private CustomProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered);
		x.view().inject(this);
		
		mContext = this;
		txtTitle.setText("用户注册");
		imgBtnLeft.setImageDrawable(getResources().getDrawable(R.drawable.back_btn));
		
		progressDialog = new CustomProgressDialog(mContext);
	}

	@Override
	public RegisterPresenter initPresenter() {
		return new RegisterPresenter();
	}
	
	@Event({R.id.imgBtnLeft, R.id.regBtn})
	private void onClick(View view) {
		switch (view.getId()) {
			// 返回
			case R.id.imgBtnLeft:
				finish();
				break;
			// 注册
			case R.id.regBtn:
				presenter.register(mContext, registerPhoneEdt.getText().toString(), registerPassWordEdt.getText().toString(), confirmPassWordEdt.getText().toString());
				break;
		}
	}
	
	@Override
	public void showLoading() {
		progressDialog.show(false);
	}
	
	@Override
	public void hideLoading() {
		progressDialog.dismiss();
	}
	
	@Override
	public void registerState(boolean isSuccessful, String msg) {
		ToastUtil.showShort(mContext, msg);
		if (isSuccessful) {
			// 注册成功回到登录界面
			Intent intent = new Intent();
			intent.putExtra("userPhone", registerPhoneEdt.getText().toString());
			intent.putExtra("password", registerPassWordEdt.getText().toString());
			RegisteredActivity.this.setResult(1, intent);
			RegisteredActivity.this.finish();
		}
	}
}
