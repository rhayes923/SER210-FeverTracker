package edu.quinnipiac.ser210.fevertracker;

import android.database.sqlite.SQLiteDatabase;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseHelperTest {
    private static DatabaseHelper myDatabaseHelper;

    @Before
    public void setUp() throws Exception {
        myDatabaseHelper = new DatabaseHelper(null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStoredTemp() {
        String temp = "101.1";
        myDatabaseHelper.setSelected(temp,null,null,null);

        assertEquals("101.1", myDatabaseHelper.getStoredTemp());
    }

    @Test
    public void getStoredDate() {
        String date = "04-20-20";
        myDatabaseHelper.setSelected(null,date,null,null);

        assertEquals("04-20-20", myDatabaseHelper.getStoredDate());
    }

    @Test
    public void getStoredTime() {
        String time = "03:36 PM";
        myDatabaseHelper.setSelected(null,null, time,null);

        assertEquals("03:36 PM", myDatabaseHelper.getStoredTime());

    }

    @Test
    public void getStoredFeeling() {
        String feeling = "6";
        myDatabaseHelper.setSelected(null,null,null,feeling);

        assertEquals("6", myDatabaseHelper.getStoredFeeling());
    }

    @Test
    public void getAllReports() {

    }
}