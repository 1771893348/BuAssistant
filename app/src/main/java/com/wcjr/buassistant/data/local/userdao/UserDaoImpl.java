package com.wcjr.buassistant.data.local.userdao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.wcjr.buassistant.data.local.db.NetHelper;

/**
 * @author wgw
 * @time 2018/9/10 10:22
 * @class describe
 */
public class UserDaoImpl implements UserDao{

    private NetHelper mNetHelper;
    @Override
    public void insert(ContentValues values) {
        SQLiteDatabase db = mNetHelper.getWritableDatabase();
        db.insert(NetHelper.TABLE_NAME, null, values);
    }

    @Override
    public void delete() {

    }
}
