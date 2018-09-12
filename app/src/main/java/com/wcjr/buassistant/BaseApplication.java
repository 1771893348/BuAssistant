package com.wcjr.buassistant;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * @author wgw
 * @time 2018/9/7 15:46
 * @class describe
 */
public class BaseApplication extends Application{
    private static BaseApplication mBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        resetDensity();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resetDensity();
    }

//    屏幕宽度是多少pt  设计图标注的宽度
    public final static float DESIGN_WIDTH = 750;

    public void resetDensity() {
        Point size = new Point();
        ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);

        getResources().getDisplayMetrics().xdpi = size.x / DESIGN_WIDTH * 72f;
    }

    public static BaseApplication  getInstance(){
        return mBaseApplication;
    }
}
