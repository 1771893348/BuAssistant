package com.wcjr.buassistant.data.local.userdao;

import android.content.ContentValues;

/**
 * @author wgw
 * @time 2018/9/10 10:20
 * @class describe
 */
public interface UserDao {
    void insert(ContentValues values);
    void delete();
}
