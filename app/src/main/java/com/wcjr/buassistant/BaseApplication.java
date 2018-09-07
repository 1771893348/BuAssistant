package com.wcjr.buassistant;

import android.app.Application;

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
    }

    public static BaseApplication  getInstance(){
        return mBaseApplication;
    }
}
