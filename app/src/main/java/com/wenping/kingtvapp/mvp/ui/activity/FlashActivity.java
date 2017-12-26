package com.wenping.kingtvapp.mvp.ui.activity;

import android.view.animation.Animation;

import com.king.base.SplashActivity;
import com.wenping.kingtvapp.R;

/**
 * Created by WenPing on 2017/12/25.
 * 完成欢迎页和Main的显示
 * <p>
 */

public class FlashActivity extends SplashActivity{



    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Animation.AnimationListener getAnimationListener() {

        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivityFinish(MainActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        return listener;
    }
}
