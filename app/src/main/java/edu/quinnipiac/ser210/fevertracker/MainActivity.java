package edu.quinnipiac.ser210.fevertracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider provider;
    private static DatabaseHelper dbHelper;
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DatabaseHelper(this);
        //deleteDatabase("RECORD");
        db = dbHelper.getWritableDatabase();

        provider = new ShareActionProvider(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_share) { //Share the record
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            ///This is where the data will be shared with doctor
            intent.putExtra(Intent.EXTRA_TEXT, dbHelper.getStoredTemp());
            intent.putExtra(Intent.EXTRA_TEXT, dbHelper.getStoredDate());
            intent.putExtra(Intent.EXTRA_TEXT, dbHelper.getStoredTime());
            intent.putExtra(Intent.EXTRA_TEXT, dbHelper.getStoredFeeling());
            provider.setShareIntent(intent);
        }

        if (id == R.id.action_about) { //say what the app is about
            Toast.makeText(getApplicationContext(),
                    "This app is meant to help track your fever\nIt will allow you to share this data with your doctor.",
                    Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public static void insert(String temp, String date, String time, String feeling) {
        dbHelper.insertRecord(db, temp, date, time, feeling);
    }

    public static List<String> displayInfo(String id) {
        return dbHelper.displayReport(id);
    }
}
