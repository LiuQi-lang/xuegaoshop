package com.example.xuegaoshop

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table account(_id integer primary key autoincrement," +  //主键
                "Title varchar(20)," +  //Title
                "Date varchar(20)," +  //Date
                "Money vaechar(20))" //Money
        db.execSQL(sql)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "account_daily.db"
    }
}