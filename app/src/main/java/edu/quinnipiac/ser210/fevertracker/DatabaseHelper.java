package edu.quinnipiac.ser210.fevertracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "feverTracker"; //The name of the database
    private static final int DB_VERSION = 1; //The version of the database

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertRecord(SQLiteDatabase db, int temp,
                                    int date, int time) {
        ContentValues feverValues = new ContentValues();
        feverValues.put("TEMPERATURE", temp);
        feverValues.put("DATE", date);
        feverValues.put("TIME", time);
        db.insert("RECORD", null, feverValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE RECORD (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "TEMPERATURE INTEGER, "
                    + "DATE INTEGER, "
                    + "TIME INTEGER); ");
        }
        if (oldVersion < 2) {

        }
    }
}
