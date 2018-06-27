package com.example.android.todo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.android.todo.db.TableCommands.CMD_CREATE_TABLE

// this is creating the database in which the table is created

class MyDbHelper(context: Context) : SQLiteOpenHelper(
        context,
        "todos",
        null,
        1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CMD_CREATE_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}