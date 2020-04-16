package edu.quinnipiac.ser210.fevertracker;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class ReportsFragment extends Fragment {

    private Spinner spinner;

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
        loadSpinnerData();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadSpinnerData() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> reports = db.getAllReports();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, reports);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
}
