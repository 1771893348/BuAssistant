package com.wcjr.buassistant.modules.paymoney

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import com.wcjr.buassistant.R
import com.wcjr.buassistant.androidlibrary.base.BaseActivity
import com.alipay.sdk.app.PayTask
import android.widget.Toast





/**
 * @author wgw
 * @time 2018/9/19 9:51
 * @class describe
 */
class PayMoney : BaseActivity(),PayMoneyContract.view,View.OnClickListener{


    var btn_pay: Button? = null
    val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val result = msg.obj as String
            Toast.makeText(this@PayMoney, result,
                    Toast.LENGTH_LONG).show()
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_paymoney
    }

    override fun initViews(savedInstanceState: Bundle?) {
        btn_pay = findViewById(R.id.btn_pay)
    }

    override fun initToolBar() {
    }

    override fun setPresenter(presenter: PayMoneyContract.Presenter?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PayMoneyPresenter()
    }

    override fun onResume() {
        super.onResume()
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn_pay->{
                val orderInfo = "wgw"   // 订单信息

                val payRunnable = Runnable {
                    val alipay = PayTask(this@PayMoney)
                    val result = alipay.payV2(orderInfo, true)

                    val msg = Message()
                    msg.what = 1
                    msg.obj = result
                    mHandler.sendMessage(msg)
                }
                // 必须异步调用
                val payThread = Thread(payRunnable)
                payThread.start()
            }

        }
    }
}
