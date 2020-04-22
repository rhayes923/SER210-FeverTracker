package edu.quinnipiac.ser210.fevertracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ReportsFragment extends Fragment {
    private SQLiteDatabase db;
    private Cursor cursor;

    //private Spinner spinner;
    private ListView listReports;
    private TextView temp, date, time, feeling;

    public ReportsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        //spinner = view.findViewById(R.id.spnrReports);
        listReports = view.findViewById(R.id.listReports);
        temp = view.findViewById(R.id.txtTemp);
        date = view.findViewById(R.id.txtDate);
        time = view.findViewById(R.id.txtTime);
        feeling = view.findViewById(R.id.txtFeeling);

        loadListData();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadListData() {
        final DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> reports = db.getAllReports();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, reports);
        listReports.setAdapter(dataAdapter);
        listReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String value = Integer.toString(position+1);
                List<String> info = MainActivity.displayInfo(value);

                String tempInfo = info.get(0);
                String dateInfo = info.get(1);
                String timeInfo = info.get(2);
                String feelingInfo = info.get(3);

                temp.setText("Temperature: " + tempInfo);
                date.setText("Date: " + dateInfo);
                time.setText("Time: " + timeInfo);
                feeling.setText("Feeling: " + feelingInfo);

                db.setSelected(tempInfo, dateInfo, timeInfo, feelingInfo);
            }
        });
    }
}
