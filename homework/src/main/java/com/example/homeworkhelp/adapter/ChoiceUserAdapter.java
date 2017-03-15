package com.example.homeworkhelp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.homeworkhelp.R;
import com.example.homeworkhelp.bean.UserBean;
import com.example.homeworkhelp.utils.ToastUtil;
import com.liji.circleimageview.CircleImageView;

import java.util.List;

/**
 * @author Administrator
 * @time 2017/3/14 0014 21:58
 */

public class ChoiceUserAdapter extends RecyclerView.Adapter<ChoiceUserAdapter.ChoiceUserHolder> {
    private Context mContext;
    private List<UserBean> userBeans;

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
    public void onBindViewHolder(ChoiceUserHolder holder, int position) {
        Glide.with(mContext).load(userBeans.get(position).getUserHead()).into(holder.userHead);
        holder.userName.setText(userBeans.get(position).getUserName());
        holder.userPhone.setText(userBeans.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return userBeans.size();
    }

    class ChoiceUserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView userHead;
        TextView userName;
        TextView userPhone;

        public ChoiceUserHolder(View itemView) {
            super(itemView);
            userHead = (CircleImageView) itemView.findViewById(R.id.userHead);
            userName = (TextView) itemView.findViewById(R.id.userNameTxt);
            userPhone = (TextView) itemView.findViewById(R.id.userPhoneTxt);
        }
    
        @Override
        public void onClick(View v) {
            ToastUtil.showShort(mContext, "asdf");
        }
    }
}
