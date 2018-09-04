package com.ahmedmakramallah.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public DBHelper dbHelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init my helper to create habit's database
        dbHelper = new DBHelper(this);
    }

    // create a method to insert habits in database
    private void insertData(String name, int number){

        dbHelper = new DBHelper(this);
        // init sqlitedatbase to insert(write) database
        db = dbHelper.getWritableDatabase();

        // insert data through ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(Habit.HabitEntities.COLUMNHABIT_NAME, name);
        contentValues.put(Habit.HabitEntities.COLUMNHABIT_NUMBER, number);

        // insert data at specified rowID
        long rowID = db.insert(Habit.HabitEntities.TABLE_NAME, null, contentValues);

        // check if a row is empty (refers it -1) , notify me throughout Toast message
        if (rowID == -1) {
            Toast.makeText(this, "Error occurred during insert a habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "habit is inserted at rowID:   " + rowID, Toast.LENGTH_SHORT).show();
        }
    }

    // create a method to show data, return "cursor"
    private Cursor displayData(){
        // before showing data, must read it
        db = dbHelper.getReadableDatabase();

        String [] projection = {Habit.HabitEntities._ID, Habit.HabitEntities.COLUMNHABIT_NAME, Habit.HabitEntities.COLUMNHABIT_NUMBER};
        Cursor cursor = db.query(Habit.HabitEntities.TABLE_NAME, projection, null, null, null, null, null);

        return cursor;
    }
}
