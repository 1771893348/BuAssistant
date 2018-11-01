package com.wcjr.buassistant.webviewbu.utils;

import android.content.Context;
import android.content.res.Resources;

import com.wcjr.buassistant.webviewbu.R;

/**
 * @author wgw
 * @time 2018/11/1 11:49
 * @class describe
 */
public class ADFilterTool {
    /**
     * 屏蔽广告的NoAdWebViewClient类
     *
     * @param context
     * @param url
     * @return true 为广告链接，false 为正常连接
     */
    public static boolean hasAd(Context context, String url) {
        Resources res = context.getResources();
        String[] adUrls = res.getStringArray(R.array.adBlockUrl);
        for (String adUrl : adUrls) {
            if (url.contains(adUrl)) {
                return true;
            }
        }
        return false;
    }

}
