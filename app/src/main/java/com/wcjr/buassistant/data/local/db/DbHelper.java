package com.wcjr.buassistant.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author wgw
 * @time 2018/9/10 9:48
 * @class describe
 */
public abstract class DbHelper extends SQLiteOpenHelper{


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createDB(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        UpgradeDb(sqLiteDatabase);
    }

    public abstract void createDB(SQLiteDatabase sqLiteDatabase);
    public abstract void UpgradeDb(SQLiteDatabase sqLiteDatabase);
}
