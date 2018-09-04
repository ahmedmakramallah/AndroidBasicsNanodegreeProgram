package com.ahmedmakramallah.inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahmed on 8/18/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "inventory.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table 's items
        String SQL = "CREATE TABLE " + Item.ItemEntites.TABLE_NAME + " ("
                + Item.ItemEntites._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Item.ItemEntites.COLUMN_NAME + " TEXT NOT NULL, "
                + Item.ItemEntites.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + Item.ItemEntites.COLUMN_PRICE + " INTEGER NOT NULL, "
                + Item.ItemEntites.COLUMN_NUMBER + " TEXT NOT NULL, "
                + Item.ItemEntites.COLUMN_IMAGE_PATH + " TEXT);";

        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
