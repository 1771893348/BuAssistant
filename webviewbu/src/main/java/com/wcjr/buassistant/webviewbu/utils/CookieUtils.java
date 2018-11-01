package com.wcjr.buassistant.webviewbu.utils;
import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebView;


/**
 * @author wgw
 * @time 2018/11/1 11:58
 * @class describe
 */


public class CookieUtils {/*


    *//**s
     * 安卓登陆与H5同步Cookie
     *
     * @param mUrl
     *//*
    public static void syncSession(Context context, WebView webView, String mUrl){
        HttpUrl httpUrl = HttpUrl.parse(mUrl);
        if (httpUrl != null){
            //注入session
            CookieManager cm = CookieManager.getInstance();
            cm.setAcceptCookie(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cm.removeSessionCookies(new ValueCallback<Boolean>() {
                    @Override
                    public void onReceiveValue(Boolean value) {
                    }
                });
            } else {
                cm.removeSessionCookie();
            }
            cm.removeAllCookie();
            JavaNetCookieJar cookieJar = (JavaNetCookieJar) RetrofitUtil.getOkHttpClient().cookieJar();
            List<Cookie> cookies = cookieJar.loadForRequest(httpUrl);
            for (Cookie cookie : cookies) {
                cm.setCookie(mUrl, cookie.toString());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cm.setAcceptThirdPartyCookies(webView,true);
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager.createInstance(context).sync();
            }
        }
    }

    *//**s
     * 安卓登陆与H5同步Cookie   X5内核同步
     *
     * @param mUrl
     *//*
    public static void syncSession(Context context, com.tencent.smtt.sdk.WebView webView, String mUrl){
        HttpUrl httpUrl = HttpUrl.parse(mUrl);
        if (httpUrl != null){
            //注入session
            com.tencent.smtt.sdk.CookieManager cm = com.tencent.smtt.sdk.CookieManager.getInstance();
            cm.setAcceptCookie(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cm.removeSessionCookies(new com.tencent.smtt.sdk.ValueCallback<Boolean>() {
                    @Override
                    public void onReceiveValue(Boolean value) {
                    }
                });
            } else {
                cm.removeSessionCookie();
            }
            cm.removeAllCookie();
            JavaNetCookieJar cookieJar = (JavaNetCookieJar) RetrofitUtil.getOkHttpClient().cookieJar();
            List<Cookie> cookies = cookieJar.loadForRequest(httpUrl);
            for (Cookie cookie : cookies) {
                cm.setCookie(mUrl, cookie.toString());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cm.setAcceptThirdPartyCookies(webView,true);
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager.createInstance(context).sync();
            }
        }
    }

    *//**
     * 同步token
     *
     * @param context
     * @param url
     * @param token
     *//*
    public static void syncCookie(Context context, String url,String token){
        clearCookie(context);
        try{
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除
            cookieManager.removeAllCookie();
            String cookieValue = "token=" + token;
            cookieManager.setCookie(url, cookieValue);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager.createInstance(context).sync();
            }
        }catch(Exception e){
        }
    }

    *//**
     * 同步X5 token
     *
     * @param context
     * @param url
     * @param token
     *//*
    public static void syncX5Cookie(Context context, String url,String token){
        clearX5Cookie(context);
        try{
            com.tencent.smtt.sdk.CookieSyncManager.createInstance(context);
            com.tencent.smtt.sdk.CookieManager cookieManager = com.tencent.smtt.sdk.CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除
            cookieManager.removeAllCookie();
            String cookieValue = "token=" + token;
            cookieManager.setCookie(url, cookieValue);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                com.tencent.smtt.sdk.CookieManager.getInstance().flush();
            } else {
                com.tencent.smtt.sdk.CookieSyncManager.createInstance(context).sync();
            }
        }catch(Exception e){
        }
    }


    *//**
     * 清除WebView 缓存以及Cookie
     *
     * @param context
     * @param webView
     *//*
    public static void clearWebViewCache(Context context, WebView webView){
        clearCookie(context);
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearCache(true);
    }

    *//**
     * 清除X5WebView 缓存以及Cookie
     *
     * @param context
     * @param webView
     *//*
    public static void clearWebViewCache(Context context, com.tencent.smtt.sdk.WebView webView){
        clearX5Cookie(context);
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearCache(true);
    }


    *//**
     * 清除Cookie
     *
     * @param context
     *//*
    public static void clearCookie(Context context){
        CookieSyncManager.createInstance(context);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// forces sync manager to sync now
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager.createInstance(context);
            CookieSyncManager.getInstance().sync();
        }
    }

    *//**
     * 清除X5Cookie
     *
     * @param context
     *//*
    public static void clearX5Cookie(Context context){
        com.tencent.smtt.sdk.CookieSyncManager.createInstance(context);  //Create a singleton CookieSyncManager within a context
        com.tencent.smtt.sdk.CookieManager cookieManager = com.tencent.smtt.sdk.CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// forces sync manager to sync now
            com.tencent.smtt.sdk.CookieManager.getInstance().flush();
        } else {
            com.tencent.smtt.sdk.CookieSyncManager.createInstance(context);
            com.tencent.smtt.sdk.CookieSyncManager.getInstance().sync();
        }
    }

    *//**
     * 停止原生WebView加载,销毁WebView
     *
     * @param webView
     *//*
    public static void destroyWebView(WebView webView){
        if (webView != null){
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }
            webView.stopLoading();
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }
    *//**
     * 停止X5WebView加载,销毁WebView
     *
     * @param webView
     *//*
    public static void destroyWebView(com.tencent.smtt.sdk.WebView webView){
        if (webView != null){
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }
            webView.stopLoading();
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }*/
}
