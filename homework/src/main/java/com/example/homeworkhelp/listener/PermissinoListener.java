package com.example.homeworkhelp.listener;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6
 * 权限申请回调监听
 */

public interface PermissinoListener {
    // 同意
    void agree();
    // 拒绝
    void refuse(List<String> refusePermission);
}
