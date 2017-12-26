package com.wenping.kingtvapp.mvp.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wenping.kingtvapp.R;
import com.wenping.kingtvapp.app.TvApp;

/**
 * Created by WenPing on 2017/12/26.
 * <p>
 */

public abstract class BaseActivity extends AppCompatActivity{


    protected Context mContext;

    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = Color.parseColor("#7f000000");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 2017/12/26
        //如果版本高于19;设置透明的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mContext = this;
        //抽象方法:填充布局
        setContentView(getRootViewId());
        //初始化布局
        initUI();
        setStatusViewColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    /**
     * 设置状态栏颜色
     * @param statusColor
     */
    private void setStatusViewColor(int statusColor) {
        //如果状态栏颜色值为实值则直接赋值,代码不往下执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                getWindow().setStatusBarColor(statusColor);
            }
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int color = COLOR_DEFAULT;
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(this));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
        }
    }

    private int getStatusBarHeight(BaseActivity activity) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(R.id.fragmentContent,fragment);
    }

    public void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id,fragment).commit();
    }

    public TvApp mApp;

    protected abstract void initUI();

    protected abstract int getRootViewId();
}








































