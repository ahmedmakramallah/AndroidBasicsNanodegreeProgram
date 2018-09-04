package com.ahmedmakramallah.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahmed on 8/13/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Inital version for habit database, need it when upgrade database
    private static final int DATABASE_VERSION = 1;
    // Database name
    private static final String DATABASE_NAME = "habits.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create habit 's database
        String SQL =  "CREATE TABLE " + Habit.HabitEntities.TABLE_NAME + " ("
                + Habit.HabitEntities._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Habit.HabitEntities.COLUMNHABIT_NAME + " TEXT NOT NULL, "
                + Habit.HabitEntities.COLUMNHABIT_NUMBER + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
