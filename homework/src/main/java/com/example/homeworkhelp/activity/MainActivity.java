package com.example.homeworkhelp.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.homeworkhelp.R;
import com.example.homeworkhelp.utils.SharedPreferencesUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		
		imgBtnLeft.setImageDrawable(getResources().getDrawable(R.drawable.back_btn));

        Glide.with(this).load("http://pic.58pic.com/58pic/12/32/79/02D58PIC9Xt.jpg").into(bgImageView);

        Glide.with(this).load("http://img.1985t.com/uploads/attaches/2012/05/5536-kBimZ3.jpg").into(userHeadImgView);
		
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
	protected void onDestroy() {
		super.onDestroy();
		// 登录成功后修改为已登录状态
		SharedPreferencesUtil.storedMessage(MainActivity.this, SharedPreferencesUtil.IS_LOGIN, false);
	}
}
