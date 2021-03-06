package com.wcjr.buassistant.webviewbu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wcjr.buassistant.webviewbu.utils.ADFilterTool;

import java.util.Map;

/**
 * @author wgw
 * @time 2018/11/1 11:11
 * @class describe
 * https://blog.csdn.net/qq_35070105/article/details/80047595
 */
public class SimpleWebView extends WebView {
    public SimpleWebView(Context context) {
        super(context);
        init();
    }

    public SimpleWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SimpleWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public SimpleWebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
        init();
    }

    public SimpleWebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
        init();
    }

    private void init(){
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        this.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
            }
        });
        this.setWebViewClient(new SimpleWebViewClient());
    }

    public static class SimpleWebViewClient extends WebViewClient {

//        private SimpleLoadingDialog loadingDialog;

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
            //做广告拦截，ADFIlterTool 为广告拦截工具类
            if (!ADFilterTool.hasAd(webView.getContext(),url)){
                return super.shouldInterceptRequest(webView, url);
            }else {
                return new WebResourceResponse(null,null,null);
            }
        }
        /**
         * 防止加载网页时调起系统浏览器
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }
        //在开始的时候，开始loadingDialog
        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            try{
//                loadingDialog = new SimpleLoadingDialog(webView.getContext(),true);
//                loadingDialog.show();
            }catch (Exception e){}
        }
        //在页面加载结束的时候，关闭LoadingDialog
        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            try {
//

            } catch (Exception e) {}
        }

        @Override
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

    }

}
