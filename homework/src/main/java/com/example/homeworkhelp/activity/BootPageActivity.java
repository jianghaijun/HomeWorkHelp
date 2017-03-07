package com.example.homeworkhelp.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.base.BaseActivity;
import com.example.homeworkhelp.listener.PermissinoListener;
import com.example.homeworkhelp.presenter.BootPagePresenter;
import com.example.homeworkhelp.view.BootPageView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6
 */
public class BootPageActivity extends BaseActivity<BootPageView, BootPagePresenter> implements BootPageView {
	private TextView txt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot_page);
		
		txt = (TextView) findViewById(R.id.txt);
		
		txt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				requestAuthority(new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALENDAR
				}, new PermissinoListener() {
					@Override
					public void agree() {
						presenter.show();
					}
					
					@Override
					public void refuse(List<String> refusePermission) {
						
					}
				});
			}
		});
	}
	
	@Override
	public BootPagePresenter initPresenter() {
		return new BootPagePresenter();
	}
	
	
	@Override
	public void showLoading() {
		
	}
	
	@Override
	public void hideLoading() {
		
	}
	
	@Override
	public void showToast() {
		Toast.makeText(this, "MVP", Toast.LENGTH_SHORT).show();
	}
}
