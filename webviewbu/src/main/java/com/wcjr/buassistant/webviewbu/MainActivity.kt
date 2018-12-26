package com.wcjr.buassistant.webviewbu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wcjr.buassistant.webviewbu.view.SimpleWebView

class MainActivity : AppCompatActivity() {
    private var  mWebView:SimpleWebView?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWebView = findViewById(R.id.webview)
        mWebView!!.loadUrl("https://www.baidu.com/")
    }
}
