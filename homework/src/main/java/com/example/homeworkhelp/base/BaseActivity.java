package com.example.homeworkhelp.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.example.homeworkhelp.listener.PermissinoListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/6
 * BaseActivity
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    // 权限申请回调监听
    private PermissinoListener perListerner;
    // MVP模式 Presenter
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = initPresenter();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

/**
     * 申请权限
     * @param permissions
     * @param perListerner
     */
    public void requestAuthority(String[] permissions, PermissinoListener perListerner) {
        this.perListerner = perListerner;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            perListerner.agree();
        }
    }

    /**
     * 权限申请回调函数
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> refusePermissionList = new ArrayList<>();

                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            refusePermissionList.add(permissions[i]);
                        }
                    }

                    if (!refusePermissionList.isEmpty()) {
                        perListerner.refuse(refusePermissionList);
                    } else {
                        perListerner.agree();
                    }
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    public abstract T initPresenter();
}
