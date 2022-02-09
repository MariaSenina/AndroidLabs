package com.example.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CustomOpener extends SQLiteOpenHelper {
    public final static String DB_NAME = "todo_app_db";
    public final static int DB_VERSION = 1;
    public final static String TABLE_NAME = "TODO_LIST";
    public final static String COL_ID = "_id";
    public final static String COL_TODO_ITEM = "TODO_ITEM";
    public final static String COL_URGENT = "URGENT";

    public CustomOpener(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TODO_ITEM + " TEXT,"
                + COL_URGENT + "TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");

        onCreate(sqLiteDatabase);
    }
}