package com.ahmedmakramallah.habittracker;

import android.provider.BaseColumns;

/**
 * Created by ahmed on 8/13/2017.
 */

public class Habit {

    public Habit(){}

    public static final class HabitEntities implements BaseColumns{

        // Database name
        public final static String TABLE_NAME = "habits";
        // ID
        public final static String _ID = BaseColumns._ID;
        // Column name
        public final static String COLUMNHABIT_NAME ="habit_name";
        // Column namber
        public final static String COLUMNHABIT_NUMBER = "habit_number";

    }
}
