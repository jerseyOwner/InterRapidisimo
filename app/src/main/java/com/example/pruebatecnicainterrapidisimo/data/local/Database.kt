package com.example.pruebatecnicainterrapidisimo.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pruebatecnicainterrapidisimo.data.network.model.SchemeResponseItem

class Database(context: Context, private val queryList: List<SchemeResponseItem>): SQLiteOpenHelper(context, "Test", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        for (query in queryList) {
            p0?.execSQL(query.queryCreacion)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        for (query in queryList) {
            p0?.execSQL("DROP TABLE IF EXISTS ${query.nombreTabla}")
        }
        onCreate(p0)
    }

    fun getTableNames2(): List<String> {

        val db = this.readableDatabase
        val query = "SELECT name FROM sqlite_master WHERE type='table' AND name!='android_metadata' order by name"
        val cursor = db.rawQuery(query, null)

        val tableNames = mutableListOf<String>()

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                tableNames.add(cursor.getString(0))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return tableNames
    }

    fun updateTables() {
        val db = this.writableDatabase
        onUpgrade(db, 1, 1)
        db.close()
    }
}