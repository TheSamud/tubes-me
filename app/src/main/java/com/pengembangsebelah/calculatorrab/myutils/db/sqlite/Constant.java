package com.pengembangsebelah.calculatorrab.myutils.db.sqlite;

public class Constant {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ReaderContract.FeedEntry.TABLE_NAME_PROJECT+ " (" +
                    ReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_NAME + " TEXT," +
                    ReaderContract.FeedEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    ReaderContract.FeedEntry.COLUMN_NAME_IDSKOEF + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ReaderContract.FeedEntry.TABLE_NAME_PROJECT;

    public static final String SQL_CREATE_ENTRIES_KOEFISIEN =
            "CREATE TABLE " + ReaderContract.FeedEntry.TABLE_KOEFISIEN+ " (" +
                    ReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ReaderContract.FeedEntry.COLUMN_NAME_PEKERJAAN + " TEXT," +
                    ReaderContract.FeedEntry.COLUMN_HARGA_PEKERJAAN + " TEXT)";
    public static final String SQL_DELETE_ENTRIES_KOEFISIEN =
            "DROP TABLE IF EXISTS " + ReaderContract.FeedEntry.TABLE_KOEFISIEN;

    public static final String SQL_CREATE_ENTRIES_PROJECT_KOEFISIEN =
            "CREATE TABLE " + ReaderContract.FeedEntry.TABLE_NAME_KOEFISIEN+ " (" +
                    ReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_NAME_KOEFISIEN + " TEXT," +
                    ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_KOEFISIEN_TOTAL + " TEXT," +
                    ReaderContract.FeedEntry.COLUMN_NAME_PEKERJAAN + " TEXT)";
    public static final String SQL_DELETE_ENTRIES_PROJECT_KOEFISIEN =
            "DROP TABLE IF EXISTS " + ReaderContract.FeedEntry.TABLE_NAME_KOEFISIEN;

    public static final String SQL_CREATE_ENTRIES_PROJECT =
            "CREATE TABLE " + ReaderContract.FeedEntry.TABLE_NAME_DATA_PROJECT+ " (" +
                    ReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN + " INTEGER," +
                    ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN2 + " TEXT," +
                    ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN3 + " TEXT," +
                    ReaderContract.FeedEntry.COLUMN_ID_KOEFISIWN + " TEXT," +
                    ReaderContract.FeedEntry.VALUE_DATA_PROJECT + " TEXT)";
    public static final String SQL_DELETE_ENTRIES_PROJECT =
            "DROP TABLE IF EXISTS " + ReaderContract.FeedEntry.TABLE_NAME_DATA_PROJECT;
}
