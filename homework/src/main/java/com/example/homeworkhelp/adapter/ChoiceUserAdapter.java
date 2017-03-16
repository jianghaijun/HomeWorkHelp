package com.example.homeworkhelp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.homeworkhelp.R;
import com.example.homeworkhelp.activity.ChoiceUserActivity;
import com.example.homeworkhelp.bean.UserBean;
import com.example.homeworkhelp.dialog.PromptMsgDialog;
import com.example.homeworkhelp.listener.PromptMsgListener;
import com.liji.circleimageview.CircleImageView;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author Administrator
 * @time 2017/3/14 0014 21:58
 */

public class ChoiceUserAdapter extends RecyclerView.Adapter<ChoiceUserAdapter.ChoiceUserHolder> {
    private Context mContext;
    private List<UserBean> userBeans;

    private int point;

    public ChoiceUserAdapter (Context mContext, List<UserBean> userBeans) {
        this.mContext = mContext;
        this.userBeans = userBeans;
    }

    @Override
    public ChoiceUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChoiceUserHolder choiceUserHolder = new ChoiceUserHolder(LayoutInflater.from(mContext).inflate(R.layout.user_item_layout, parent, false));
        return choiceUserHolder;
    }

    @Override
    public void onBindViewHolder(ChoiceUserHolder holder, final int position) {
        Glide.with(mContext).load(userBeans.get(position).getUserHead()).into(holder.userHead);
        holder.userName.setText(userBeans.get(position).getUserName());
        holder.userPhone.setText(userBeans.get(position).getPhone());

        // item 长按事件
        holder.userInfoRl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                point = position;
                PromptMsgDialog promptMsgDialog = new PromptMsgDialog(mContext, "提示", "是否刪除该账号?", "是", "否", promptMsgListener);
                promptMsgDialog.show();
                return false;
            }
        });

        // 头像点击事件
        holder.userHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				ChoiceUserActivity.jumpActivity(userBeans.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userBeans.size();
    }

    class ChoiceUserHolder extends RecyclerView.ViewHolder {
        private RelativeLayout userInfoRl;
        private CircleImageView userHead;
        private TextView userName;
        private TextView userPhone;

        public ChoiceUserHolder(View itemView) {
            super(itemView);
            userInfoRl = (RelativeLayout) itemView.findViewById(R.id.userInfoRl);
            userHead = (CircleImageView) itemView.findViewById(R.id.userHead);
            userName = (TextView) itemView.findViewById(R.id.userNameTxt);
            userPhone = (TextView) itemView.findViewById(R.id.userPhoneTxt);
        }

    }

    /**
     * 长按监听
     */
    private PromptMsgListener promptMsgListener = new PromptMsgListener() {
        @Override
        public void isAgree(boolean isAgree) {
            if (isAgree) {
                Logger.w("删除" + userBeans.get(point).getPhone());
                int num = DataSupport.deleteAll(UserBean.class, "phone=?", userBeans.get(point).getPhone());
                if (num > 0) {
                    userBeans.remove(point);
                    ChoiceUserAdapter.this.notifyDataSetChanged();
                }
            }
        }
    };
}
