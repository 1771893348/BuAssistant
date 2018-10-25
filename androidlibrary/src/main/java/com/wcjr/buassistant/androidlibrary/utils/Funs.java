package com.wcjr.buassistant.androidlibrary.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.wcjr.buassistant.androidlibrary.R;

import java.util.List;

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

    public void jump(Activity activity,String url, String parm, int animId){


        Intent intent=new Intent("", Uri.parse(url));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("", parm);

        PackageManager packageManager=activity.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);

        if (! resolveInfos.isEmpty())
        {
            activity.startActivity(intent);
//            selectTranlateAnim(activity, animId);
        }else {
        }
    }
}
