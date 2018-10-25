package com.wcjr.buassistant.modules.loginpage

import android.app.Activity
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentActivity
import android.support.v4.app.NotificationCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import android.view.ViewStub
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RemoteViews
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
        notice(this)

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

    fun notice(context: Context) {
        val builder = NotificationCompat.Builder(context)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sina.com"))

        val pi = PendingIntent.getActivity(this, 0, intent, 0)

        // 未下拉的样式 R.layout.collapsed
        val collapsed = RemoteViews(packageName, R.layout.collapsed)
        collapsed.setTextViewText(R.id.collapsed_text, "关闭状态")

        //下拉后的样式R.layout.show
        val show = RemoteViews(packageName, R.layout.notice_show)


        val notify = builder.setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setContentText("新浪微博")
                .setCustomContentView(collapsed)//下拉前
                .setCustomBigContentView(show)//下拉后
                .build()

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, notify)
    }
}
