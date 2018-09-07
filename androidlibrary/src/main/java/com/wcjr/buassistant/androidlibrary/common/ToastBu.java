package com.wcjr.buassistant.androidlibrary.common;

import android.content.Context;
import android.widget.Toast;

/**
 * @author wgw
 * @time 2018/9/7 10:55
 * @class describe
 */
public class ToastBu {
    private static ToastBu mToastBu;
    private Toast mToast;
    public ToastBu(Context context){
        mToast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT);;
    }

    public static ToastBu getInstance(Context context){
        if (mToastBu == null) {
            synchronized (ToastBu.class){
                if (mToastBu == null) {
                    mToastBu = new ToastBu(context);
                }
            }
        }
        return mToastBu;
    }

    public ToastBu setShowTime(int time){
        mToast.setDuration(time);
        return mToastBu;
    }
    public ToastBu setShowMsg(String msg){
        mToast.setText(msg);
        return mToastBu;
    }
    public void show(){
        mToast.show();
    }


}
