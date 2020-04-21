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

    private Spinner spinner;
    private ListView listReports;
    private TextView temp, date, time;

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
        spinner = view.findViewById(R.id.spnrReports);
        listReports = view.findViewById(R.id.listReports);
        temp = view.findViewById(R.id.txtTemp);
        date = view.findViewById(R.id.txtDate);
        time = view.findViewById(R.id.txtTime);

        //loadSpinnerData();
        loadListData();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadListData() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> reports = db.getAllReports();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, reports);
        listReports.setAdapter(dataAdapter);
        listReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String value = Integer.toString(position+1);
                List<String> info = MainActivity.displayInfo(value);
                temp.setText("Temperature: " + info.get(0));
                date.setText("Date: " + info.get(1));
                time.setText("Time: " + info.get(2));
            }
        });
    }

    //Not used anymore. Was used when a spinner object was used to display records.
    private void loadSpinnerData() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> reports = db.getAllReports();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, reports);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(spinner.getCount()-1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = spinner.getSelectedItem().toString();
                List<String> info = MainActivity.displayInfo(value);
                temp.setText("Temperature: " + info.get(0));
                date.setText("Date: " + info.get(1));
                time.setText("Time: " + info.get(2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


}
