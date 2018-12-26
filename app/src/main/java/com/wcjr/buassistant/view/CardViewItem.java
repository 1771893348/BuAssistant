package com.wcjr.buassistant.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wcjr.buassistant.R;

import java.util.ArrayList;

/**
 * @author wgw
 * @time 2018/12/24 11:18
 * @class describe
 */
public class CardViewItem extends LinearLayout {
    private TextView card_itemview_name;
    private TextView card_itemview_num1;
    private TextView card_itemview_num2;
    LayoutInflater mLayoutInflater;
    public CardViewItem(Context context) {
        super(context);
        init(context);
    }

    public CardViewItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CardViewItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CardViewItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }
    private void itemView(){

        View item = mLayoutInflater.inflate(R.layout.card_itemview,null);
        card_itemview_name = item.findViewById(R.id.card_itemview_name);
        card_itemview_num1 = item.findViewById(R.id.card_itemview_num1);
        card_itemview_num2 = item.findViewById(R.id.card_itemview_num2);

    }
    public void addItemViewList(ArrayList<ItemBean> list){
        for (ItemBean bean: list) {
            addItemView(bean);
        }
    }
    public void addItemView(ItemBean itemBean){
        View item = mLayoutInflater.inflate(R.layout.card_itemview,null);
        card_itemview_name = item.findViewById(R.id.card_itemview_name);
        card_itemview_num1 = item.findViewById(R.id.card_itemview_num1);
        card_itemview_num2 = item.findViewById(R.id.card_itemview_num2);
        card_itemview_name.setText(itemBean.getItemName());
        card_itemview_num1.setText(itemBean.getItemNum1());
        card_itemview_num2.setText(itemBean.getItemNum2());
        this.addView(item);
    }
   public static class ItemBean{
        String itemName;
        String itemNum1;
        String itemNum2;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemNum1() {
            return itemNum1;
        }

        public void setItemNum1(String itemNum1) {
            this.itemNum1 = itemNum1;
        }

        public String getItemNum2() {
            return itemNum2;
        }

        public void setItemNum2(String itemNum2) {
            this.itemNum2 = itemNum2;
        }
    }
}
