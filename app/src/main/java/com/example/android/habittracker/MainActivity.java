package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.habittracker.data.Contract;
import com.example.android.habittracker.data.HabitsDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dummy-Content to test methods
        insertHabit("Drink Water", 1);
        insertHabit("Read a Book", 0);
        insertHabit("Bla!", 1);

        // printing out database, using the readHabits-method
        System.out.println("id - name - counter - reminder");
        Cursor cursor = readHabits();

        // we look up the column index once here to save performance
        int idColumn = cursor.getColumnIndex(Contract.Habits._ID);
        int nameColumn = cursor.getColumnIndex(Contract.Habits.COLUMN_HABITS_NAME);
        int counterColumn = cursor.getColumnIndex(Contract.Habits.COLUMN_HABITS_COUNTER);
        int reminderColumn = cursor.getColumnIndex(Contract.Habits.COLUMN_HABITS_REMINDER);

        try {
            while (cursor.moveToNext()) {
                System.out.println(
                        cursor.getInt(idColumn) + " - "
                                + cursor.getString(nameColumn) + " - "
                                + cursor.getInt(counterColumn) + " - "
                                + cursor.getInt(reminderColumn)
                );
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * inserts the two necessary values into a new entry. the other columns will be filled out
     * automatically.
     * @param name is the name of the habit (e.g. 'read a book')
     * @param reminder can have value 1 or 0.
     *                 1 = user wants to be reminded to do his habit.
     *                 0 = no reminder wished.
     */
    public void insertHabit(@NonNull String name, Integer reminder){
        ContentValues newHabit = new ContentValues();
        newHabit.put(Contract.Habits.COLUMN_HABITS_NAME, name);
        newHabit.put(Contract.Habits.COLUMN_HABITS_REMINDER, reminder);

        HabitsDbHelper mDbHelper = new HabitsDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.insert(Contract.Habits.TABLE_NAME, null, newHabit);
    }

    /**
     * reads the habits-database, returns it unfiltered.
     * @return Cursor for the database. Don't forget to close the cursor!
     */
    public Cursor readHabits(){
        SQLiteDatabase db = new HabitsDbHelper(this).getReadableDatabase();
        return db.query(Contract.Habits.TABLE_NAME, null, null, null, null, null, null);
    }
}
