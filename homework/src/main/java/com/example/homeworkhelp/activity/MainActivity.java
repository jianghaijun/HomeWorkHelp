package com.example.homeworkhelp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.homeworkhelp.R;
import com.example.homeworkhelp.adapter.SettingAdapter;
import com.example.homeworkhelp.bean.SettingBean;
import com.example.homeworkhelp.custom.RecyclerViewDivider;
import com.example.homeworkhelp.utils.SharedPreferencesUtil;
import com.umeng.socialize.UMShareAPI;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 * @author JiangHaiJun
 * @time 2017/3/7 14:55
 */
public class MainActivity extends AppCompatActivity {
	@ViewInject(R.id.imgBtnLeft)
	private ImageButton imgBtnLeft;
	@ViewInject(R.id.drawelayout)
	private DrawerLayout drawelayout;
    @ViewInject(R.id.img_user_head)
    private ImageView userHeadImgView;
    @ViewInject(R.id.bg_imgview)
    private ImageView bgImageView;
	@ViewInject(R.id.user_name)
	private TextView txtUserName;
	@ViewInject(R.id.setting_recycle_view)
	private RecyclerView setttingView;

	private SettingAdapter settingAdapter;
	private List<SettingBean> settingBeens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		
		initDrawable();
		
		initDate();
		
		settingAdapter = new SettingAdapter(this, settingBeens);
		setttingView.setLayoutManager(new LinearLayoutManager(this));
		setttingView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));
		setttingView.setAdapter(settingAdapter);
		
	}

	private void initDrawable() {
		imgBtnLeft.setImageDrawable(getResources().getDrawable(R.drawable.back_btn));
		// 菜单栏背景
		Glide.with(this).load("http://pic.58pic.com/58pic/12/32/79/02D58PIC9Xt.jpg").into(bgImageView);
		// 用户头像
		Glide.with(this).load("http://img.1985t.com/uploads/attaches/2012/05/5536-kBimZ3.jpg").into(userHeadImgView);
	}

	private void initDate() {
		settingBeens = new ArrayList<>();
		SettingBean bean1 = new SettingBean();
		bean1.setImgId(R.drawable.perm_group_camera);
		bean1.setTitle("分享");
		SettingBean bean2 = new SettingBean();
		bean2.setImgId(R.drawable.perm_group_screenlock);
		bean2.setTitle("修改密码");
		SettingBean bean3 = new SettingBean();
		bean3.setImgId(R.drawable.perm_group_system_tools);
		bean3.setTitle("修改昵称");
		settingBeens.add(bean1);
		settingBeens.add(bean2);
		settingBeens.add(bean3);
		txtUserName.setText("利率");
	}
	
	@Event({ R.id.imgBtnLeft })
	private void onClick(View view) {
		switch (view.getId()) {
			case R.id.imgBtnLeft:
				drawelayout.openDrawer(GravityCompat.START);
				break;
		}
			
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
	}

@Override
	protected void onDestroy() {
		super.onDestroy();
		// 登录成功后修改为已登录状态
		SharedPreferencesUtil.storedMessage(MainActivity.this, SharedPreferencesUtil.IS_LOGIN, false);
	}
}
