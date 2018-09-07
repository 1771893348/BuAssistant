package com.wcjr.buassistant.androidlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * @author wgw
 * @time 2018/9/7 10:06
 * @class describe
 */
public class Funs {
    /**
     * @param drawableId
     * @return
     */
    public static Drawable getDrawable(Context context,int drawableId) {
        return ContextCompat.getDrawable(context, drawableId);
    }
    /**
     * 关闭键盘
     */
    public static void hideKeyBoard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void showToastCenter(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
