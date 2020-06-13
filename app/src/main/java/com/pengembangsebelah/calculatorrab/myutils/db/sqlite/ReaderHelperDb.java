package com.pengembangsebelah.calculatorrab.myutils.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.pengembangsebelah.calculatorrab.myutils.db.sqlite.Constant.SQL_CREATE_ENTRIES;
import static com.pengembangsebelah.calculatorrab.myutils.db.sqlite.Constant.SQL_CREATE_ENTRIES_KOEFISIEN;
import static com.pengembangsebelah.calculatorrab.myutils.db.sqlite.Constant.SQL_CREATE_ENTRIES_PROJECT;
import static com.pengembangsebelah.calculatorrab.myutils.db.sqlite.Constant.SQL_CREATE_ENTRIES_PROJECT_KOEFISIEN;
import static com.pengembangsebelah.calculatorrab.myutils.db.sqlite.Constant.SQL_DELETE_ENTRIES;
import static com.pengembangsebelah.calculatorrab.myutils.db.sqlite.Constant.SQL_DELETE_ENTRIES_KOEFISIEN;
import static com.pengembangsebelah.calculatorrab.myutils.db.sqlite.Constant.SQL_DELETE_ENTRIES_PROJECT_KOEFISIEN;

public class ReaderHelperDb extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app.db";

    public ReaderHelperDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_KOEFISIEN);
        db.execSQL(SQL_CREATE_ENTRIES_PROJECT_KOEFISIEN);
        db.execSQL(SQL_CREATE_ENTRIES_PROJECT);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_KOEFISIEN);
        db.execSQL(SQL_DELETE_ENTRIES_PROJECT_KOEFISIEN);
        db.execSQL(SQL_CREATE_ENTRIES_PROJECT);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
