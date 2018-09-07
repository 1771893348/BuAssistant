package com.wcjr.buassistant.modules.loginpage

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.ViewStub
import android.widget.Button
import com.wcjr.buassistant.R
import com.wcjr.buassistant.androidlibrary.common.ToastBu
import com.wcjr.buassistant.androidlibrary.utils.WGWLog

class MainActivity : Activity() {
    var mContext: Context?=null
    var login_stub: ViewStub? = null
    var login:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        initView()

        login_stub?.inflate()
        if (login == null){
            ToastBu.getInstance(mContext).setShowMsg("hello world").show()
        }
        login?.text="点我"
        login?.setOnClickListener{
            WGWLog.d("onClick","--========================")
            ToastBu.getInstance(mContext).setShowMsg("hello world").show()
        }
    }

    fun initView(){
        login_stub = findViewById(R.id.login_stub)
        login = findViewById(R.id.login)
    }
}
