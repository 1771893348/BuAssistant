package com.wcjr.buassistant.modules.loginpage

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import android.view.ViewStub
import android.widget.Button
import android.widget.FrameLayout
import com.wcjr.buassistant.R
import com.wcjr.buassistant.androidlibrary.common.ToastBu
import com.wcjr.buassistant.androidlibrary.utils.WGWLog
import com.wcjr.buassistant.modules.home.HomePage

class MainActivity : FragmentActivity(),NavigationView.OnNavigationItemSelectedListener {


    var mContext: Context?=null
    internal var mFlContainer: FrameLayout? = null
    internal var mNavView: NavigationView? = null
    internal var mDrawerLayout: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mContext = this
        initView()


    }

    fun initView(){
        mFlContainer = findViewById(R.id.fl_container)
        mNavView = findViewById(R.id.nav_view)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, HomePage()).commit()
    }
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
