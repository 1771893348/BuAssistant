package com.wcjr.buassistant.modules.home


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wcjr.buassistant.R
import com.wcjr.buassistant.androidlibrary.base.BaseLazyFragment
import com.wcjr.buassistant.androidlibrary.utils.WGWLog
import com.wcjr.buassistant.modules.paymoney.PayMoney
import com.wcjr.buassistant.view.CardViewItem

/**
 * @author wgw
 * @time 2018/9/19 10:31
 * @class describe
 */
class HomePage : BaseLazyFragment(){


    var btn_topay_activity:Button?=null
    var CardLearn:CardViewItem ?=null
    override fun finishCreateView(state: Bundle?) {
        btn_topay_activity!!.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent()
            intent.setClass(activity,PayMoney::class.java)
            activity!!.startActivity(intent)
        })
        var list:ArrayList<CardViewItem.ItemBean> = ArrayList()
        for (i in 1..5){
            WGWLog.d("ArrayList","==="+i)
            var it:CardViewItem.ItemBean = CardViewItem.ItemBean()
            it.itemName="wgw"+i
            it.itemNum1 = (i+10).toString()
            it.itemNum2 =i.toString()
            list.add(it)
        }
        CardLearn!!.addItemViewList(list)
    }
    override fun initView(view: View?) {
        btn_topay_activity = view!!.findViewById(R.id.btn_topay_activity)
        CardLearn = view!!.findViewById(R.id.CardLearn)
    }
    override fun getLayoutResId(): Int {

        return R.layout.fragment_home
    }

}