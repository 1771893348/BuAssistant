package com.wcjr.buassistant.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author wgw
 * @time 2018/9/10 9:59
 * @class describe
 */
public class NetHelper extends DbHelper{
    private static final String NETDB="net.db";
    private static final int NETVERTION = 1;
    public static final String TABLE_NAME = "file_info";
    private static final String CREATE_TABLE = "create table "+TABLE_NAME+" ( "
            + "id integer primary key autoincrement, "
            + "url text, "
            + "name text, "
            + "path text, "
            + "loadBytes integer, "
            + "totalBytes integer, "
            + "status integer)";
    private static final String DROP_TABLE = "drop table if exists file_info";
    public NetHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public NetHelper(Context context) {
        super(context, NETDB, null, NETVERTION);
    }
    @Override
    public void createDB(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void UpgradeDb(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DROP_TABLE);
    }
}
