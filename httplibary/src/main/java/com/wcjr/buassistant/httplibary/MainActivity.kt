package com.wcjr.buassistant.httplibary

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WgwVolly.Builder().setUrl("http://api.map.baidu.com/telematics/v3/weather?location=嘉兴&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ").setType("GET").setHttpCallBack(object: HttpCallBack {
            override fun success(response: String?) {
                Log.d("wgw_success",response)
            }

            override fun fail(error: String?) {
                Log.d("wgw_fail",error)
            }

        }).Build().sentRequest(null)
    }
}
