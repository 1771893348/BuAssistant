package com.wcjr.buassistant.modules.home


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wcjr.buassistant.R
import com.wcjr.buassistant.androidlibrary.base.BaseLazyFragment
import com.wcjr.buassistant.modules.paymoney.PayMoney

/**
 * @author wgw
 * @time 2018/9/19 10:31
 * @class describe
 */
class HomePage : BaseLazyFragment(){


    var btn_topay_activity:Button?=null
    override fun finishCreateView(state: Bundle?) {
        btn_topay_activity!!.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent()
            intent.setClass(activity,PayMoney::class.java)

            activity!!.startActivity(intent)
        })
    }
    override fun initView(view: View?) {
        btn_topay_activity = view!!.findViewById(R.id.btn_topay_activity)
    }
    override fun getLayoutResId(): Int {

        return R.layout.fragment_home
    }

}