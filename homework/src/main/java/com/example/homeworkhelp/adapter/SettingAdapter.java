package com.example.homeworkhelp.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.bean.SettingBean;

import java.util.List;

/**
 * @author JiangHaiJun
 * @time 2017/3/30 14:25
 */

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingHolder> {
	private Activity mContext;
	private LayoutInflater mInflater;
	private List<SettingBean> settingBeen;

	public SettingAdapter(Activity mContext, List<SettingBean> settingBeen){
		mInflater = LayoutInflater.from(mContext);
		this.mContext = mContext;
		this.settingBeen = settingBeen;
	}

	@Override
	public SettingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new SettingHolder(mInflater.inflate(R.layout.setting_item_layout, parent, false));
	}
	
	@Override
	public void onBindViewHolder(SettingHolder holder, int position) {
		Drawable drawable = mContext.getResources().getDrawable(settingBeen.get(position).getImgId());
		// 必须必须执行drawable.setBounds才能生效
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		holder.setting.setCompoundDrawables(drawable, null, null, null);
		holder.setting.setText(settingBeen.get(position).getTitle());
	
	}
	
	@Override
	public int getItemCount() {
		return settingBeen.size();
	}

	public class SettingHolder extends  RecyclerView.ViewHolder{
		private Button setting;
		
		public SettingHolder(View itemView) {
			super(itemView);
			setting = (Button) itemView.findViewById(R.id.setting);
		}
	}

}
