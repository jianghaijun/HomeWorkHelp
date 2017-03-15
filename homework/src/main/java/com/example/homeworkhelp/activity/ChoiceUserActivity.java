package com.example.homeworkhelp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.homeworkhelp.R;
import com.example.homeworkhelp.adapter.ChoiceUserAdapter;
import com.example.homeworkhelp.base.BaseActivity;
import com.example.homeworkhelp.bean.UserBean;
import com.example.homeworkhelp.presenter.ChoiceUserPresenter;
import com.example.homeworkhelp.view.ChoiceUserView;

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

        choiceUserAdapter = new ChoiceUserAdapter(this, userBeans);
        userRclView.setLayoutManager(new LinearLayoutManager(this));
        userRclView.setAdapter(choiceUserAdapter);

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
                break;
        }
    }
}
