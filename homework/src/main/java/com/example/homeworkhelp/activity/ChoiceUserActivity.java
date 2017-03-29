package com.example.homeworkhelp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.adapter.ChoiceUserAdapter;
import com.example.homeworkhelp.base.BaseActivity;
import com.example.homeworkhelp.bean.UserBean;
import com.example.homeworkhelp.custom.RecyclerViewDivider;
import com.example.homeworkhelp.presenter.ChoiceUserPresenter;
import com.example.homeworkhelp.view.ChoiceUserView;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

import org.litepal.crud.DataSupport;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 选择用户界面
 * @author JiangHaiJun
 * @time 2017/3/14 21:00
 */
public class ChoiceUserActivity extends BaseActivity<ChoiceUserView, ChoiceUserPresenter> implements ChoiceUserView {
    @ViewInject(R.id.txtTitle)
    private TextView txtTitle;
    @ViewInject(R.id.imgBtnLeft)
    private ImageButton imgBtnLeft;
    @ViewInject(R.id.rightTxt)
    private TextView rightTxt;

    @ViewInject(R.id.userRclView)
    private RecyclerView userRclView;
    @ViewInject(R.id.swipeRefreshLayout)
    private SHSwipeRefreshLayout swipeRefreshLayout;

    private static Activity mContext;
    private ChoiceUserAdapter choiceUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_user);
        x.view().inject(this);

        txtTitle.setText("用户选择");
        imgBtnLeft.setImageDrawable(getResources().getDrawable(R.drawable.back_btn));
        rightTxt.setVisibility(View.VISIBLE);
        rightTxt.setText("新用户");

        List<UserBean> userBeans = DataSupport.findAll(UserBean.class);
    
        mContext = this;
    
        initSwipeRefreshLayout();
        
        choiceUserAdapter = new ChoiceUserAdapter(this, userBeans);
        userRclView.setLayoutManager(new LinearLayoutManager(this));
        userRclView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));
        userRclView.setAdapter(choiceUserAdapter);
    
    }

	/**
	 * 设置上拉加载下拉刷新
	 */
	private void initSwipeRefreshLayout() {
        swipeRefreshLayout = (SHSwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View view = inflater.inflate(R.layout.refresh_view, null);
        final TextView textView = (TextView) view.findViewById(R.id.title);
        swipeRefreshLayout.setFooterView(view);
        swipeRefreshLayout.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
				
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.finishRefresh();
                        Toast.makeText(ChoiceUserActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1600);
            }
            
            @Override
            public void onLoading() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.finishLoadmore();
                        Toast.makeText(ChoiceUserActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1600);
            }
            
            /**
             * 监听下拉刷新过程中的状态改变
             * @param percent 当前下拉距离的百分比（0-1）
             * @param state 分三种状态{NOT_OVER_TRIGGER_POINT：还未到触发下拉刷新的距离；OVER_TRIGGER_POINT：已经到触发下拉刷新的距离；START：正在下拉刷新}
             */
            @Override
            public void onRefreshPulStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setLoaderViewText("下拉刷新");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setLoaderViewText("松开刷新");
                        break;
                    case SHSwipeRefreshLayout.START:
                        swipeRefreshLayout.setLoaderViewText("正在刷新");
                        break;
                }
            }
            
            @Override
            public void onLoadmorePullStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        textView.setText("上拉加载");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        textView.setText("松开加载");
                        break;
                    case SHSwipeRefreshLayout.START:
                        textView.setText("正在加载...");
                        break;
                }
            }
        });
    }

    /**
     * 跳转到登录界面
     * @param userBean
     */
    public static void jumpActivity(UserBean userBean){
        Intent intent = new Intent();
        intent.putExtra("user", userBean);
        mContext.setResult(1, intent);
        mContext.finish();
    }

    @Override
    public ChoiceUserPresenter initPresenter() {
        return new ChoiceUserPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getUserInfo() {

    }

    @Event({R.id.imgBtnLeft, R.id.rightTxt})
    private void onClick(View view){
        switch (view.getId()) {
            case R.id.imgBtnLeft:
                finish();
                break;
            case R.id.rightTxt:
                this.finish();
                break;
        }
    }
}
