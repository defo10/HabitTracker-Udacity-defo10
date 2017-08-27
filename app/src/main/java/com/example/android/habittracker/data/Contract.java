package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Our Contract-Class. Here are the names of the tables and their columns defined.
 */

public final class Contract {

    // there's no need for this class to be instanced
    private Contract(){}

    public static final class Habits implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABITS_NAME = "name";
        public final static String COLUMN_HABITS_COUNTER = "counter";
        public final static String COLUMN_HABITS_REMINDER = "reminder";

        // SQL-Command:
        // CREATE TABLE habits (_ID INTEGER, name TEXT, counter INTEGER, reminder INTEGER)
        public final static String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_HABITS_NAME + " TEXT NOT NULL, "
                + COLUMN_HABITS_COUNTER + " INTEGER DEFAULT 0, "
                + COLUMN_HABITS_REMINDER + " INTEGER)";

    }

}
