package com.wcjr.buassistant.androidlibrary.utils;

import android.util.Log;

/**
 * @author wgw
 * @time 2018/9/7 14:51
 * @class describe
 */
public class WGWLog {
    private static boolean isDebug = true;
    public static void d(String tag,String msg){
        if (isDebug){
            Log.d("wgw_"+tag,msg);
        }

    }
}
