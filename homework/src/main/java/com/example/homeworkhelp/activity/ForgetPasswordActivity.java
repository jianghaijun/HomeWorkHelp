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
import com.example.homeworkhelp.presenter.ForgetPasswordPresenter;
import com.example.homeworkhelp.utils.PhoneFormatCheckUtils;
import com.example.homeworkhelp.utils.ToastUtil;
import com.example.homeworkhelp.view.ForgetPasswordView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 忘记密码
 * @author JiangHaiJun
 * @time 2017/3/14 10:27
 */
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordView, ForgetPasswordPresenter> implements ForgetPasswordView {
	@ViewInject(R.id.txtTitle)
	private TextView txtTitle;
	@ViewInject(R.id.imgBtnLeft)
	private ImageButton imgBtnLeft;
	@ViewInject(R.id.userPhoneEdt)
	private EditText userPhone;
	@ViewInject(R.id.newPassWordEdt)
	private EditText newPassWord;
	@ViewInject(R.id.confirmNewPassWordEdt)
	private EditText confirmNewPassWord;
	@ViewInject(R.id.smsCodeEdt)
	private EditText smsCode;

	private Context mContext;
	private CustomProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		x.view().inject(this);
		
		mContext = this;
		progressDialog = new CustomProgressDialog(this);
		
		txtTitle.setText("修改密码");
		imgBtnLeft.setImageDrawable(getResources().getDrawable(R.drawable.back_btn));
	}

	@Override
	public ForgetPasswordPresenter initPresenter() {
		return new ForgetPasswordPresenter();
	}
	
	@Override
	public void showLoading() {
		progressDialog.show();
	}
	
	@Override
	public void hideLoading() {
		progressDialog.dismiss();
	}
	
	@Override
	public void sendSmsCode(String smsCode) {
		ToastUtil.showLong(mContext, smsCode);
	}
	
	@Override
	public void modifyResult(String msg, int code) {
		switch (code) {
			case 0:
				ToastUtil.showShort(mContext, msg);
				Intent intent = new Intent();
				intent.putExtra("userPhone", userPhone.getText().toString());
				intent.putExtra("password", newPassWord.getText().toString());
				ForgetPasswordActivity.this.setResult(1, intent);
				ForgetPasswordActivity.this.finish();
				break;
			default:
				ToastUtil.showShort(mContext, msg);
				break;
		}
	}
	
	@Event({R.id.imgBtnLeft, R.id.sendSmsCodeBtn, R.id.modifyPassWordBtn})
	private void onClick(View view) {
		switch (view.getId()) {
			case R.id.imgBtnLeft:
				finish();
				break;
			// 发送短信验证码
			case R.id.sendSmsCodeBtn:
				if (userPhone.getText().toString().isEmpty()) {
					ToastUtil.showLong(mContext, "请输入手机号!");
				} else if (!PhoneFormatCheckUtils.isPhoneLegal(userPhone.getText().toString())){
					ToastUtil.showLong(mContext, "请输入正确的手机号!");
				} else {
					presenter.sendSmsCode();
				}
				break;
			// 修改密码
			case R.id.modifyPassWordBtn:
				presenter.modifyPassWord(mContext, userPhone.getText().toString(), newPassWord.getText().toString(), confirmNewPassWord.getText().toString(), smsCode.getText().toString());
				break;
		}
	}
}
