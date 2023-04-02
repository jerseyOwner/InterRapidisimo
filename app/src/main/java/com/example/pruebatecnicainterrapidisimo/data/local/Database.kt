package com.example.pruebatecnicainterrapidisimo.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.pruebatecnicainterrapidisimo.data.network.model.SchemeResponseItem

class Database(context: Context, private val queryList: List<String>): SQLiteOpenHelper(context, "Test", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        for (query in queryList) {
            p0?.execSQL(query)
        }
        val namesTable = "CREATE TABLE Tables (" +
                "name varchar(100) NOT NULL UNIQUE" +
                ")"
        p0?.execSQL(namesTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
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

    fun saveTableName(table: SchemeResponseItem): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put("name", table.nombreTabla)

        return db.insert("Tables", null, contentValues)
    }

    fun getTableNames(): MutableList<String> {

        val tableNameList = mutableListOf<String>()
        val db = this.readableDatabase
        val query = "SELECT * FROM Tables"

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            return mutableListOf()
        }

        var name: String

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                tableNameList.add(name)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tableNameList
    }
}