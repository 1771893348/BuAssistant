package com.wcjr.buassistant.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wcjr.buassistant.R;

/**
 * @author wgw
 * @time 2018/12/24 11:18
 * @class describe
 */
public class CardViewItem extends LinearLayout {
    private TextView card_itemview_name;
    private TextView card_itemview_num1;
    private TextView card_itemview_num2;
    public CardViewItem(Context context) {
        super(context);
    }

    public CardViewItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardViewItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CardViewItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context){
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        View item = mLayoutInflater.inflate(R.layout.card_itemview,null);

    }
}
